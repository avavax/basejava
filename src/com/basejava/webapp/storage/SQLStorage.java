package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.*;
import com.basejava.webapp.sql.SQLHelper;

import java.sql.*;
import java.util.*;

public class SQLStorage implements Storage {
    private final SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ? )")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            doInsertContacts(conn, resume);
            doInsertSections(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM resume r \n" +
                    " LEFT JOIN contact c ON r.uuid = c.resume_uuid \n" +
                    " WHERE r.uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    doAddContact(rs, resume);
                } while (rs.next());
            }
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    doAddSection(rs, resume);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", (ps) -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            doRemoveContacts(conn, resume.getUuid());
            doInsertContacts(conn, resume);
            doRemoveSections(conn, resume.getUuid());
            doInsertSections(conn, resume);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume r \n" +
                    " LEFT JOIN contact c ON r.uuid = c.resume_uuid \n" +
                    " ORDER BY r.full_name, r.uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
                    doAddContact(rs, map.computeIfAbsent(uuid, item -> new Resume(uuid.trim(), fullName)));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    doAddSection(rs, map.get(rs.getString("resume_uuid")));
                }
            }
            return new ArrayList<>(map.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void doInsertContacts(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void doInsertSections(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
                SectionType type = e.getKey();
                String sectionValue = null;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        sectionValue = ((SimpleSection) e.getValue()).getDescription();
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        StringBuilder sb = new StringBuilder();
                        for(String str : ((ListSection) e.getValue()).getList()) {
                            sb.append(str  + '\n');
                        }
                        sectionValue = sb.toString();
                        break;
                    case EXPERIENCE:
                    case EDUCATION:

                        break;
                }
                if (sectionValue != null) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, type.name());
                    ps.setString(3, sectionValue);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        }
    }

    private void doRemoveContacts(Connection conn, String uuid) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void doRemoveSections(Connection conn, String uuid) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM section WHERE resume_uuid=?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void doAddContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            resume.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void doAddSection(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        SectionType type = SectionType.valueOf(rs.getString("type"));
        if (value != null) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    resume.addSection(type, new SimpleSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    List<String> list = Arrays.asList(value.split("\n"));
                    resume.addSection(type, new ListSection(list));
                    break;
                case EXPERIENCE:
                case EDUCATION:

                    break;
            }
        }
    }
}
