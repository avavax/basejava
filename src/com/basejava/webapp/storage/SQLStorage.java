package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SQLHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQLStorage implements Storage {
    private final SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            doInsert(conn, resume);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r \n" +
                                " LEFT JOIN contact c ON r.uuid = c.resume_uuid \n" +
                                " WHERE r.uuid = ?",
                (ps) -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        resume.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
                    } while (rs.next());
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
            doRemove(conn, resume.getUuid());
            doInsert(conn, resume);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume r \n" +
                                " LEFT JOIN contact c ON r.uuid = c.resume_uuid \n" +
                                " ORDER BY r.full_name, r.uuid",
                this::doGetResumes);
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", (ps) -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private List<Resume> doGetResumes(PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        List<Resume> list = new ArrayList<>();
        String prevUuid = null;
        Resume resume = null;
        while (rs.next()) {
            String uuid = rs.getString("uuid").trim();
            if (!uuid.equals(prevUuid)) {
                resume = new Resume(uuid.trim(), rs.getString("full_name"));
                list.add(resume);
                prevUuid = uuid;
            }
            resume.addContact(ContactType.valueOf(rs.getString("type")), rs.getString("value"));
        }
        return list;
    }

    private void doInsert(Connection conn, Resume resume) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ? )")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        }
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?, ?, ?)")) {
            for (Map.Entry<ContactType, String> el: resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, el.getKey().name());
                ps.setString(3, el.getValue());
                ps.execute();
            }
        }
    }

    private void doRemove(Connection conn, String uuid) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        }
    }

}
