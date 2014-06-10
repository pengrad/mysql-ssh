package com.pengrad.mysqlssh.core;

import com.jcraft.jsch.JSchException;

/**
 * User: stas
 * Date: 10.06.14 16:34
 */

public class ConnectionException extends Exception {

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}