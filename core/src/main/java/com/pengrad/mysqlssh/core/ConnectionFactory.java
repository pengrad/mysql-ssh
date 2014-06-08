package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.JSchException;

import java.sql.SQLException;

/**
 * User: stas
 * Date: 06.06.14 20:38
 */
public interface ConnectionFactory {

    Connection openConnection(String host, String user, String password, String database, Integer port)
            throws SQLException;

    Connection openSSHConnection(String sshHost, String sshUser, String sshPassword, Integer sshPort, String host,
                                 String user, String password, String database, Integer port) throws SQLException, JSchException;

}
