package com.basejava.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLTransaction<K> {
    K apply (Connection conn) throws SQLException;
}
