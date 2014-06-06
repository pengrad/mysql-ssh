package com.pengrad.mysqlssh.core;

import java.sql.Connection;

/**
 * User: stas
 * Date: 06.06.14 20:38
 */
public interface ConnectionManager {

    Connection openConnection();

    Connection openSSHConnection();

    void closeConnection();

}
