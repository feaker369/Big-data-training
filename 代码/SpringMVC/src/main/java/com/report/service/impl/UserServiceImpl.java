package com.report.service.impl;
import com.report.model.*;
import com.report.service.UserService;
import com.report.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }
    public User getUser(int id){
        return userMapper.getUser(id);
    }
    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
    @Override
    public List<User> getProUser(int id) {
        return userMapper.getProUser(id);
    }
    /**
     * 获取用户渠道趋势分析维度
     * @return
     */
    public List<String> getVAreaChannelDim(){
        return userMapper.getVAreaChannelDim();
    }
    /**
     * 用户渠道趋势分析
     * @return
     */
    @Override
    public List<VAreaChannel> getVAreaChannel(String dim) {
        return userMapper.getVAreaChannel(dim);
    }
    /**
     * 用户渠道趋势分析
     * @return
     */
    @Override
    public List<VAreaChannel> getVAreaChannelAll() {
        return userMapper.getVAreaChannelAll();
    }
    /**
     * 获取用户请求方式对比情况维度
     * @return
     */
    public List<String> getVAreaRequestTypeDim(){
        return userMapper.getVAreaRequestTypeDim();
    }
    /**
     * 用户请求方式对比情况
     * @return
     */
    @Override
    public List<VAreaRequesttype> getVAreaRequestType(String dim) {
        return userMapper.getVAreaRequestType(dim);
    }
    /**
     * 用户请求方式对比情况
     * @return
     */
    @Override
    public List<VAreaRequesttype> getVAreaRequestTypeAll() {
        return userMapper.getVAreaRequestTypeAll();
    }
    /**
     * 用户渠道饼图情况
     * @return
     */
    @Override
    public List<VChannelno> getVChannelno(){
        return userMapper.getVChannelno();
    }
    @Override
    public List<VUserLogin> getVUserLogin() {
        return userMapper.getVUserLogin();
    }
    public List<VUserDetail> getDetail(String areaname,String channelname){
        return userMapper.getDetail(areaname,channelname);
    }
}