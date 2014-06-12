package com.pengrad.mysqlssh.core;

import java.util.ArrayList;
import java.util.List;

/**
 * User: stas
 * Date: 11.06.14 20:49
 */

public class QueryResult {

    public static final int RESULT_TYPE_RESULTSET = 1;
    public static final int RESULT_TYPE_ROWCOUNT = 2;
    public static final int RESULT_TYPE_NONE = 3;

    private int resultType;
    private int rowCount;
    private List<String> columnNames;
    private List<List<Object>> resultSet;

    public QueryResult() {
        resultType = RESULT_TYPE_NONE;
        rowCount = 0;
        columnNames = new ArrayList<>(0);
        resultSet = new ArrayList<>(0);
    }

    public int getResultType() {
        return resultType;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<List<Object>> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<List<Object>> resultSet) {
        this.resultSet = resultSet;
    }
}
