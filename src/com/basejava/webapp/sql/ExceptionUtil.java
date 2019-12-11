package com.basejava.webapp.sql;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private static final String SQL_EXIST_ERROR = "23505";

    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

            if (e.getSQLState().equals(SQL_EXIST_ERROR)) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}