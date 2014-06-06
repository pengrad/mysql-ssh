package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.Connection;
import java.util.Properties;

/**
 * User: stas
 * Date: 06.06.14 20:49
 */

public class JSchConnectionManager implements ConnectionManager {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No mysql driver com.mysql.jdbc.Driver", e);
        }
    }

    private JSch jsch;
    private Properties sshConfig;
    private Session sshSession;

    public JSchConnectionManager() {
        jsch = new JSch();
        sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
    }

    public Connection openConnection() {
        return null;
    }

    public Connection openSSHConnection() {
        return null;
    }

    public void closeConnection() {
        if (sshSession != null) sshSession.disconnect();
    }

    private Session doSshTunnel(String user, String pass, String host, int portLocal, int portRemote) throws JSchException {
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(pass);
        session.setConfig(sshConfig);
        session.connect();
        session.setPortForwardingL(portLocal, host, portRemote);
        return session;
    }
}
