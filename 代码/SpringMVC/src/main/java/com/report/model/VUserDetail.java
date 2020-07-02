package com.report.model;


/**
 * 用户表
 */
public class VUserDetail {

    private String imei;
    private String requestip;
    private String requesttypename;
    private String first_login_time;
    private int log_times;
    private int online_time;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRequestip() {
        return requestip;
    }

    public void setRequestip(String requestip) {
        this.requestip = requestip;
    }

    public String getRequesttypename() {
        return requesttypename;
    }

    public void setRequesttypename(String requesttypename) {
        this.requesttypename = requesttypename;
    }

    public String getFirst_login_time() {
        return first_login_time;
    }

    public void setFirst_login_time(String first_login_time) {
        this.first_login_time = first_login_time;
    }

    public int getLog_times() {
        return log_times;
    }

    public void setLog_times(int log_times) {
        this.log_times = log_times;
    }

    public int getOnline_time() {
        return online_time;
    }

    public void setOnline_time(int online_time) {
        this.online_time = online_time;
    }
}