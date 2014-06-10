package com.pengrad.mysqlssh.core;

/**
 * User: stas
 * Date: 06.06.14 20:38
 */
public interface ConnectionFactory {

    Connection openConnection(ConnectionProperties connectionProperties) throws ConnectionException;

}
