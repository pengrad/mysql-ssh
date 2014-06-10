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

    public Connection openConnection(ConnectionProperties connectionProperties) throws SQLException, JSchException {
        String host = connectionProperties.getHost();
        String user = connectionProperties.getUser();
        String password = connectionProperties.getPassword();
        String database = connectionProperties.getDatabase();
        int port = connectionProperties.getPort() != null ? connectionProperties.getPort() : 3306;
        Session session = null;
        if (connectionProperties.isSSH()) {
            String sshHost = connectionProperties.getSshHost();
            String sshUser = connectionProperties.getSshUser();
            String sshPassword = connectionProperties.getSshPassword();
            int sshPort = connectionProperties.getSshPort() != null ? connectionProperties.getSshPort() : 22;
            session = doSshTunnel(sshHost, sshUser, sshPassword, sshPort, port, port);
        }
        String url = "jdbc:mysql:/" + host + ":" + port + "/" + (database != null ? database : "mysql");
        java.sql.Connection connection = DriverManager.getConnection(url, user, password);
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
