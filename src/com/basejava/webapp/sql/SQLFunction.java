package com.basejava.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SQLFunction<K> {
    K apply(PreparedStatement p) throws SQLException;
}
