package com.basejava.webapp.util;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {
    private static final String SQL_EXIST_ERROR = "23505";
    private final ConnectionFactory connectionFactory;

    public SQLHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <K> K doGetResult(String statement, GetFunction<K> maker) throws StorageException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            return maker.apply(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals(SQL_EXIST_ERROR)) {
                throw new ExistStorageException(null);
            }
            throw new StorageException(e);
        }
    }

    public interface GetFunction<K> {
        K apply(PreparedStatement p) throws SQLException;
    }
}
