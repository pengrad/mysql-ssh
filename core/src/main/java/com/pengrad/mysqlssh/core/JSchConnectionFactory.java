package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: stas
 * Date: 06.06.14 20:49
 */

public class JSchConnectionFactory implements ConnectionFactory {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No mysql driver com.mysql.jdbc.Driver", e);
        }
    }

    private JSch jsch;
    private Properties sshConfig;

    public JSchConnectionFactory() {
        jsch = new JSch();
        sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
    }

    public Connection openConnection(String host, String user, String password, String database, Integer port) throws SQLException {
        StringBuilder url = new StringBuilder("jdbc:mysql:/")
                .append(host)
                .append(":")
                .append(port != null ? port : 3306)
                .append("/")
                .append(database != null ? database : "mysql");
        java.sql.Connection connection = DriverManager.getConnection(url.toString(), user, password);
        return new JSchConnection(connection, null);
    }

    public Connection openSSHConnection(String sshHost, String sshUser, String sshPassword, Integer sshPort,
                                        String host, String user, String password, String database, Integer port) throws SQLException, JSchException {
        int _sshPort = sshPort != null ? sshPort : 22;
        int _port = port != null ? port : 3306;
        Session session = doSshTunnel(sshHost, sshUser, sshPassword, _sshPort, _port, _port);
        StringBuilder url = new StringBuilder("jdbc:mysql:/")
                .append(host)
                .append(":")
                .append(_port)
                .append("/")
                .append(database != null ? database : "mysql");
        java.sql.Connection connection = DriverManager.getConnection(url.toString(), user, password);
        return new JSchConnection(connection, session);
    }

    private Session doSshTunnel(String host, String user, String pass, int port, int portLocal, int portRemote) throws JSchException {
        Session session = jsch.getSession(user, host, port);
        session.setPassword(pass);
        session.setConfig(sshConfig);
        session.connect();
        session.setPortForwardingL(portLocal, host, portRemote);
        return session;
    }

}
