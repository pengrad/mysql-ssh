package com.pengrad.mysqlssh.core;

import java.sql.ResultSet;

/**
 * User: stas
 * Date: 11.06.14 20:49
 */

public class QueryResult {

    public static final int RESULT_TYPE_RESULTSET = 1;
    public static final int RESULT_TYPE_ROWCOUNT = 2;

    public int getResultType() {
        return RESULT_TYPE_RESULTSET;
    }

    public ResultSet getResultSet() {
        return null;
    }

}
