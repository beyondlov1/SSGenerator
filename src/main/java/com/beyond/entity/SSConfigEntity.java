package com.beyond.entity;

/**
 * @author beyondlov1
 * @date 2018/11/18
 */
public class SSConfigEntity {
    private String ip;
    private String port;
    private String password;
    private String encryption;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    @Override
    public String toString() {
        return "com.beyond.entity.SSConfigEntity{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                ", encryption='" + encryption + '\'' +
                '}';
    }
}
