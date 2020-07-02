package com.report.mapping;
import com.report.model.*;
import java.util.List;

public interface UserMapper {
    int insertUser(User user);
    User getUser(int id);
    List<User> getUsers();
    List<User> getProUser(int id);
    /**
     
     * @return
     */
    List<String> getVAreaChannelDim();
    /**
     
     * @return
     */
    List<VAreaChannel> getVAreaChannel(String dim);
    /**
     
     * @return
     */
    List<VAreaChannel> getVAreaChannelAll();
    /**
     
     * @return
     */
    List<String> getVAreaRequestTypeDim();
    /**
     
     * @return
     */
    List<VAreaRequesttype> getVAreaRequestType(String dim);
    /**
     
     * @return
     */
    List<VAreaRequesttype> getVAreaRequestTypeAll();
    /**
     
     * @return
     */
    List<VChannelno> getVChannelno();
    List<VUserLogin> getVUserLogin();
    List<VUserDetail> getDetail(String areaname,String channelname);
}