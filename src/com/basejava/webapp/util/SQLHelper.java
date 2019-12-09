package com.basejava.webapp.util;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLHelper {

    public static void doExecute(ConnectionFactory connectionFactory, String statement, Function maker) throws StorageException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            maker.apply(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public static <K> K doGetResult(ConnectionFactory connectionFactory, String statement, GetFunction<K> maker) throws StorageException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            return maker.apply(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface Function {
        void apply(PreparedStatement p) throws SQLException;
    }

    public interface GetFunction<K> {
        K apply(PreparedStatement p) throws SQLException;
    }

}
