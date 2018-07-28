package com.hzgc.common.collect.facedis;

import java.io.Serializable;

public class FtpRegisterInfo implements Serializable{

    private String proxyIP;
    private String proxyPort;
    private String pathRule;
    private String ftpAccountName;
    private String ftpPassword;
    private String ftpIPAddress;
    private String ftpHomeName;
    private String ftpPort;
    private String ftpType;

    public FtpRegisterInfo(){}

    public FtpRegisterInfo(String proxyIP,
                           String proxyPort,
                           String pathRule,
                           String ftpAccountName,
                           String ftpPassword,
                           String ftpIPAddress,
                           String ftpHomeName,
                           String ftpPort,
                           String ftpType) {
        this.proxyIP = proxyIP;
        this.proxyPort = proxyPort;
        this.pathRule = pathRule;
        this.ftpAccountName = ftpAccountName;
        this.ftpPassword = ftpPassword;
        this.ftpIPAddress = ftpIPAddress;
        this.ftpHomeName = ftpHomeName;
        this.ftpPort = ftpPort;
        this.ftpType = ftpType;
    }

    public String getProxyIP() {
        return proxyIP;
    }

    public void setProxyIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getPathRule() {
        return pathRule;
    }

    public void setPathRule(String pathRule) {
        this.pathRule = pathRule;
    }

    public String getFtpAccountName() {
        return ftpAccountName;
    }

    public void setFtpAccountName(String ftpAccountName) {
        this.ftpAccountName = ftpAccountName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getFtpIPAddress() {
        return ftpIPAddress;
    }

    public void setFtpIPAddress(String ftpIPAddress) {
        this.ftpIPAddress = ftpIPAddress;
    }

    public String getFtpHomeName() {
        return ftpHomeName;
    }

    public void setFtpHomeName(String ftpHomeName) {
        this.ftpHomeName = ftpHomeName;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpType() {
        return ftpType;
    }

    public void setFtpType(String ftpType) {
        this.ftpType = ftpType;
    }

    @Override
    public String toString() {
        return "FtpRegisterInfo{" +
                "proxyIP='" + proxyIP + '\'' +
                ", proxyPort=" + proxyPort +
                ", pathRule='" + pathRule + '\'' +
                ", ftpAccountName='" + ftpAccountName + '\'' +
                ", ftpPassword='" + ftpPassword + '\'' +
                ", ftpIPAddress='" + ftpIPAddress + '\'' +
                ", ftpHomeName='" + ftpHomeName + '\'' +
                ", ftpPort='" + ftpPort + '\'' +
                ", ftpType='" + ftpType + '\'' +
                '}';
    }
}
