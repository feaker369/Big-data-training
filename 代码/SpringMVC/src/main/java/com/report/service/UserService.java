package com.report.service;
import com.report.model.*;
import java.util.List;
public interface UserService {
    int insertUser(User user);
    User getUser(int id);
    List<User> getUsers();
    List<User> getProUser(int id);
    /**
     * 获取用户渠道趋势分析维度
     * @return
     */
    List<String> getVAreaChannelDim();
    /**
     * 用户渠道趋势分析
     * @return
     */
    List<VAreaChannel> getVAreaChannel(String dim);
    /**
     * 用户渠道趋势分析
     * @return
     */
    List<VAreaChannel> getVAreaChannelAll();
    /**
     * 获取用户请求方式对比情况维度
     * @return
     */
    List<String> getVAreaRequestTypeDim();
    /**
     * 用户请求方式对比情况
     * @return
     */
    List<VAreaRequesttype> getVAreaRequestType(String dim);
    /**
     * 用户请求方式对比情况
     * @return
     */
    List<VAreaRequesttype> getVAreaRequestTypeAll();
    /**
     * 用户渠道饼图情况
     * @return
     */
    List<VChannelno> getVChannelno();
    List<VUserLogin> getVUserLogin();
    List<VUserDetail> getDetail(String areaname,String channelname);
}