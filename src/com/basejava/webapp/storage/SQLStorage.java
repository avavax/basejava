package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.util.SQLHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {
    private final SQLHelper sqlHelper;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SQLHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.doGetResult("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.doGetResult("INSERT INTO resume (uuid, full_name) VALUES (?, ? )", (item) -> {
            item.setString(1, resume.getUuid());
            item.setString(2, resume.getFullName());
            item.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.doGetResult("SELECT * FROM resume WHERE uuid = ?", (item) -> {
            item.setString(1, uuid);
            ResultSet rs = item.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.doGetResult("DELETE FROM resume WHERE uuid = ?", (item) -> {
            item.setString(1, uuid);
            if (item.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.doGetResult("UPDATE resume SET full_name = ? WHERE uuid = ?", (item) -> {
            item.setString(1, resume.getFullName());
            item.setString(2, resume.getUuid());
            if (item.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.doGetResult("SELECT * FROM resume ORDER BY full_name, uuid", (item) -> {
            ResultSet rs = item.executeQuery();
            List<Resume> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.doGetResult("SELECT COUNT(*) FROM resume", (item) -> {
            ResultSet rs = item.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
