package com.pengrad.mysqlssh.core;

/**
 * User: stas
 * Date: 08.06.14 16:29
 */
public interface Connection {

    java.sql.Connection getSQLConnection();

    boolean isSSH();

    void close();

}
