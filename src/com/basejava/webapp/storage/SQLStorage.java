package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.ConnectionFactory;
import com.basejava.webapp.util.SQLHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SQLStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SQLHelper.doExecute(connectionFactory, "DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume resume) {
        SQLHelper.doExecute(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?, ? )", (item) -> {
            item.setString(1, resume.getUuid());
            item.setString(2, resume.getFullName());
            item.execute();
        });
    }

    @Override
    public Resume get(String uuid) {
        return SQLHelper.doGetResult(connectionFactory, "SELECT * FROM resume WHERE uuid = ?", (item) -> {
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
        SQLHelper.doExecute(connectionFactory, "DELETE FROM resume WHERE uuid = ?", (item) -> {
            item.setString(1, uuid);
            if (item.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
        });
    }

    @Override
    public void update(Resume resume) {
        SQLHelper.doExecute(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?", (item) -> {
            item.setString(1, resume.getFullName());
            item.setString(2, resume.getUuid());
            if (item.executeUpdate() == 0) {
                throw new NotExistStorageException(resume.getUuid());
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return SQLHelper.doGetResult(connectionFactory, "SELECT * FROM resume ORDER BY full_name", (item) -> {
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
        return SQLHelper.doGetResult(connectionFactory, "SELECT COUNT(*) FROM resume", (item) -> {
            ResultSet rs = item.executeQuery();
            int count = 0;
            if(rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        });
    }
}
