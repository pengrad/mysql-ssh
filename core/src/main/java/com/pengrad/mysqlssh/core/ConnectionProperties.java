package com.pengrad.mysqlssh.core;

/**
 * User: stas
 * Date: 10.06.14 15:52
 */

public class ConnectionProperties {

    private String name;

    // SQL
    private String host;
    private String user;
    private String password;
    private String database;
    private Integer port;

    // SSH
    private boolean isSSH;
    private String sshHost;
    private String sshUser;
    private String sshPassword;
    private Integer sshPort;

    private ConnectionProperties(String name, String host, String user, String password, String database, Integer port) {
        this.name = name;
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
        this.isSSH = false;
    }

    private ConnectionProperties(String name, String host, String user, String password, String database, Integer port,
                                 String sshHost, String sshUser, String sshPassword, Integer sshPort) {
        this(name, host, user, password, database, port);
        this.isSSH = true;
        this.sshHost = sshHost;
        this.sshUser = sshUser;
        this.sshPassword = sshPassword;
        this.sshPort = sshPort;
    }

    public static ConnectionProperties create(String name, String host, String user, String password, String database, Integer port) {
        return new ConnectionProperties(name, host, user, password, database, port);
    }

    public static ConnectionProperties createSSH(String name, String host, String user, String password, String database, Integer port,
                                                 String sshHost, String sshUser, String sshPassword, Integer sshPort) {
        return new ConnectionProperties(name, host, user, password, database, port, sshHost, sshUser, sshPassword, sshPort);
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public Integer getPort() {
        return port;
    }

    public boolean isSSH() {
        return isSSH;
    }

    public String getSshHost() {
        return sshHost;
    }

    public String getSshUser() {
        return sshUser;
    }

    public String getSshPassword() {
        return sshPassword;
    }

    public Integer getSshPort() {
        return sshPort;
    }
}
