package com.zss.commons.support.ftp;

public class FtpProperties {

    private String ftpHost;
    private int ftpPort;
    private String ftpUserName;
    private String ftpPassword;

    public FtpProperties(){}

    public FtpProperties(String ftpHost, int ftpPort, String ftpUserName, String ftpPassword){
        this.ftpHost = ftpHost;
        this.ftpPort = ftpPort;
        this.ftpUserName = ftpUserName;
        this.ftpPassword = ftpPassword;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public int getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(int ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }
}
