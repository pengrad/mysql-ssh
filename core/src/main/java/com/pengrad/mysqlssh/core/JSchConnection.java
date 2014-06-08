package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.Session;

import java.sql.SQLException;

/**
 * User: stas
 * Date: 08.06.14 16:33
 */

public class JSchConnection implements Connection {

    private java.sql.Connection sqlConnection;
    private Session sshSession;

    public JSchConnection(java.sql.Connection sqlConnection, Session sshSession) {
        this.sqlConnection = sqlConnection;
        this.sshSession = sshSession;
    }

    public java.sql.Connection getSQLConnection() {
        return sqlConnection;
    }

    public boolean isSSH() {
        return sshSession != null;
    }

    public void close() {
        try {
            sqlConnection.close();
        } catch (SQLException e) {
            // can throw up, only for show error message
        }
        if (sshSession != null) sshSession.disconnect();
    }
}
