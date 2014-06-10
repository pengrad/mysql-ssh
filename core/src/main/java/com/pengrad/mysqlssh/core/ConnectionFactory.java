package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.JSchException;

import java.sql.SQLException;

/**
 * User: stas
 * Date: 06.06.14 20:38
 */
public interface ConnectionFactory {

    Connection openConnection(ConnectionProperties connectionProperties) throws SQLException, JSchException;

}
