package com.report.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.report.model.*;
import com.report.service.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login",produces = "text/html;charset=UTF-8")
    public @ResponseBody String login(){
        return "";
    }

    @RequestMapping("charts")
    public ModelAndView charts() {
        //获取用户渠道趋势分析维度
        JSONObject jsonObjectDim_AreaChannel = new JSONObject();
        List<String> channelDim = userService.getVAreaChannelDim();
        for (int i=0; i < channelDim.size();i++){
            JSONArray jsonArray = new JSONArray();
            List<VAreaChannel> vAreaChannelList = userService.getVAreaChannel(channelDim.get(i));
            for (int j =0;j<vAreaChannelList.size();j++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("areaname",vAreaChannelList.get(j).getAreaname());
                jsonObject.put("num",vAreaChannelList.get(j).getNum());
                jsonArray.add(jsonObject);
            }
            jsonObjectDim_AreaChannel.put(channelDim.get(i),jsonArray);
        }
        //测试输出
        System.out.println(jsonObjectDim_AreaChannel);

        //用户请求方式对比情况维度
        JSONObject jsonObjectDim_AreaRequestType = new JSONObject();
        List<String> requestTypeDim = userService.getVAreaRequestTypeDim();
        for (int i=0; i < requestTypeDim.size();i++){
            JSONArray jsonArray = new JSONArray();
            List<VAreaRequesttype> vAreaRequesttypeList = userService.getVAreaRequestType(requestTypeDim.get(i));
            for (int j =0;j<vAreaRequesttypeList.size();j++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("areaname",vAreaRequesttypeList.get(j).getAreaname());
                jsonObject.put("num",vAreaRequesttypeList.get(j).getNum());
                jsonArray.add(jsonObject);
            }
            jsonObjectDim_AreaRequestType.put(requestTypeDim.get(i),jsonArray);
        }
        //用户渠道饼图情况
        List<VChannelno> channelnoList = userService.getVChannelno();

        //用户渠道趋势分析
        ModelAndView mad = new ModelAndView("charts");
        mad.addObject("areaChannelData",jsonObjectDim_AreaChannel.toJSONString());

        mad.addObject("areaChannelList",channelDim);
        //用户请求方式对比情况
        mad.addObject("areaRequestTypeData",jsonObjectDim_AreaRequestType.toJSONString());
        mad.addObject("areaRequestTypeList",requestTypeDim);
        //用户渠道饼图情况
        mad.addObject("channelnoList",channelnoList);
        // 用户渠道趋势分析
        mad.addObject("areaChannelListAll",userService.getVAreaChannelAll());

        mad.addObject("areaRequestTypeListAll",userService.getVAreaRequestTypeAll());
        mad.addObject("userLoginAll",userService.getVUserLogin());
        return mad;
    }

    @RequestMapping("detail")
    public ModelAndView detail(@RequestParam(value = "areaname") String areaname,@RequestParam(value = "channelname") String channelname) {
        ModelAndView mad = new ModelAndView("detail");
        mad.addObject("userDetailList",userService.getDetail(areaname,channelname));
        return mad;
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    public static void main(String args[]) {
        System.out.println("Hello World!");
        UserController User = new UserController();
        User.charts();
    }
}