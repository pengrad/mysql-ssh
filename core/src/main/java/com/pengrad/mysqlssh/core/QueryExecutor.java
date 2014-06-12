package com.pengrad.mysqlssh.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * User: stas
 * Date: 11.06.14 20:46
 */

public class QueryExecutor {

    public QueryResult execute(Statement statement, String query) throws SQLException {
        boolean res = statement.execute(query);
        QueryResult queryResult = new QueryResult();
        if (res) {
            queryResult.setResultType(QueryResult.RESULT_TYPE_RESULTSET);
            ResultSet resultSet = statement.getResultSet();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            queryResult.setColumnNames(columnNames);

            List<List<Object>> data = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<Object> row = new ArrayList<>(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }
            queryResult.setResultSet(data);
        } else {

        }
        return queryResult;
    }

}
