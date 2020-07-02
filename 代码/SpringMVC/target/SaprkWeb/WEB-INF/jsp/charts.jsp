<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/echarts.js" ></script>
    <title>趋势分析</title>
    <style type="text/css">
        body,table{
            font-size:12px;
        }
        table{
            table-layout:fixed;
            empty-cells:show;
            border-collapse: collapse;
            margin:0 auto;
        }
        td{
            height:30px;
        }
        h1,h2,h3{
            font-size:12px;
            margin:0;
            padding:0;
        }
        .table{
            border:1px solid #cad9ea;
            color:#666;
        }
        .table th {
            background-repeat:repeat-x;
            height:30px;
        }
        .table td,.table th{
            border:1px solid #cad9ea;
            padding:0 1em 0;
        }
        .table tr.alter{
            background-color:#f5fafe;
        }
        .chart_table{
            min-width: 900px;
        }
    </style>
</head>
<body>
    <table class="chart_table">
        <tr>
            <td style="min-width: 600px;">
                <div id="areaChannel" style="width: 600px;height:400px;"></div>
            </td>
            <td style="min-width: 300px; margin: 0px; padding: 0px;">
                <table class="table">
                    <tr>
                        <th style="width: 100px;">涨跌详情</th>
                        <th style="width: 100px;">贴水率详情</th>
                        <th style="width: 100px;">用户数</th>
                    </tr>
                    <c:forEach items="${areaChannelListAll }" var="u" varStatus="status">
                        <tr>
                            <td style="width: 100px; text-align: center;">${u.areaname }</td>
                            <td style="width: 100px; text-align: center;">${u.channelname }</td>
                            <td style="width: 100px; text-align: center;">
                                <a href="detail?areaname=${u.areaname }&channelname=${u.channelname }">${u.num }</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        //用户渠道趋势分析数据
        var areaChannelData = '${areaChannelData}';
        var objAreaChannelData = JSON.parse(areaChannelData);
        //alert(objAreaChannelData)  //没有问题
        //设置legend
        var legend = {};
        var areaChannelList = [];
        //设置xAxis横坐标
        var xAxis = {};
        xAxis.type= 'category';
        xAxis.boundaryGap=false;
        //折线
        var series = [];
        var areaChannelList = '${areaChannelList}';
        //alert(areaChannelList) //可能有问题

        <c:forEach items="${areaChannelList}" var="u" varStatus="status">
            //legend列表
            areaChannelList[<c:out value="${status.index}"/>] = '${u }';
            //横坐标数组
            var areanameArray=[];
            //指标值
            var numArray=[];
            var seriesObj = {};
            seriesObj.name = '${u}';
            seriesObj.type = 'line';
            // seriesObj.stack = '总量';
            for (var i = 0;i<objAreaChannelData.${u}.length;i++){
                areanameArray[i] = objAreaChannelData.${u}[i].areaname;
                numArray[i] = objAreaChannelData.${u}[i].num;
            }
            seriesObj.data = numArray ;
            series[<c:out value="${status.index}"/>] =seriesObj;
        </c:forEach>

        xAxis.data = areanameArray;
        legend.data = areaChannelList;
        // 基于准备好的dom，初始化echarts实例
        var areaChannel = echarts.init(document.getElementById('areaChannel'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '股票交易所类型趋势分析'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: []
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: []
            },
            yAxis: {
                type: 'value'
            },
            series: []
        };

        // 使用刚指定的配置项和数据显示图表。
        areaChannel.setOption(option);
        option.legend = legend;
        option.xAxis = xAxis;
        option.series = series;
        areaChannel.setOption(option,true);
    </script>
    <hr />
    <table class="chart_table">
        <tr>
            <td style="min-width: 600px;">
                <div id="areaRequestType" style="width: 600px;height:400px;"></div>
            </td>
            <td style="min-width: 300px; margin: 0px; padding: 0px;">
                <table class="table">
                    <tr>
                        <th style="width: 100px;">涨跌详情</th>
                        <th style="width: 100px;">交易所类型</th>
                        <th style="width: 100px;">用户数</th>
                    </tr>
                    <c:forEach items="${areaRequestTypeListAll }" var="u" varStatus="status">
                        <tr>
                            <td style="width: 100px; text-align: center;">${u.areaname }</td>
                            <td style="width: 100px; text-align: center;">${u.requesttypename }</td>
                            <td style="width: 100px; text-align: center;">${u.num }</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        //用户渠道趋势分析数据
        var areaRequestTypeData = '${areaRequestTypeData}';
        var objAreaRequestTypeData = JSON.parse(areaRequestTypeData);
        //设置legend
        var legend = {};
        var areaRequestTypeList = [];
        //设置xAxis横坐标
        var xAxis = {};
        xAxis.type= 'category';
        // xAxis.boundaryGap=false;
        //折线
        var series = [];
        <c:forEach items="${areaRequestTypeList }" var="u" varStatus="status">
            //legend列表
            areaRequestTypeList[<c:out value="${status.index}"/>] = '${u }';
            //横坐标数组
            var areanameArray=[];
            //指标值
            var numArray=[];
            var seriesObj = {};
            seriesObj.name = '${u}';
            seriesObj.type = 'bar';
        // seriesObj.stack = '总量';
            for (var i = 0;i<objAreaRequestTypeData.${u }.length;i++){
                areanameArray[i] = objAreaRequestTypeData.${u }[i].areaname;
                numArray[i] = objAreaRequestTypeData.${u }[i].num;
            }
            seriesObj.data = numArray ;
            series[<c:out value="${status.index}"/>] =seriesObj;
        </c:forEach>
        xAxis.data = areanameArray;
        legend.data = areaRequestTypeList;
        // 基于准备好的dom，初始化echarts实例
        var areaRequestType = echarts.init(document.getElementById('areaRequestType'));
        var option = {
            title : {
                text: '股票贴水率详情对比情况'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:[]
            },
            toolbox: {
                show : true,
                feature : {
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : []
        };
        // 使用刚指定的配置项和数据显示图表。
        option.legend = legend;
        option.xAxis = xAxis;
        option.series = series;
        areaRequestType.setOption(option,true);
    </script>
    <hr />
    <table class="chart_table">
        <tr>
            <td style="min-width: 600px;">
                <div id="channelno" style="width: 600px;height:400px;"></div>
            </td>
            <td style="min-width: 300px; margin: 0px; padding: 0px;">
                <table class="table">
                    <tr>
                        <th style="width: 100px;">贴水率详情</th>
                        <th style="width: 100px;">用户数</th>
                    </tr>
                    <c:forEach items="${channelnoList }" var="u" varStatus="status">
                        <tr>
                            <td style="width: 100px; text-align: center;">${u.channelname }</td>
                            <td style="width: 100px; text-align: center;">${u.num }</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        //设置legend
        var legend = {};
        var channelnoList = [];
        //
        var series = [];
        var seriesObj = {};
        seriesObj.name = '贴水率';
        seriesObj.type = 'pie';
        seriesObj.center = ['50%', '60%'];
        seriesObj.data = [];
        seriesObj.itemStyle = {};
        seriesObj.itemStyle.emphasis = {};
        seriesObj.itemStyle.emphasis.shadowBlur = 10;
        seriesObj.itemStyle.emphasis.shadowOffsetX = 0;
        seriesObj.itemStyle.emphasis.shadowColor = 'rgba(0, 0, 0, 0.5)';
        <c:forEach items="${channelnoList }" var="u" varStatus="status">
            //legend列表
            channelnoList[<c:out value="${status.index}"/>] = '${u.channelname }';
            var channelnoDate = {};
            channelnoDate.value = ${u.num };
            channelnoDate.name = '${u.channelname }';
            seriesObj.data[<c:out value="${status.index}"/>] = channelnoDate ;
        </c:forEach>
        series[0] =seriesObj;
        legend.left = 'left';
        legend.orient = 'vertical';
        legend.data = channelnoList;

        // 基于准备好的dom，初始化echarts实例
        var channelno = echarts.init(document.getElementById('channelno'));
        var option = {
            title : {
                text: '股票贴水率饼图情况',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: []
            },
            series : []
        };
        // 使用刚指定的配置项和数据显示图表。
        option.legend = legend;
        option.series = series;
        channelno.setOption(option,true);
    </script>
    <hr />
    <table class="table">
        <tr>
            <th style="width: 100px;">编号</th>
            <th style="width: 100px;">成交量</th>
            <th style="width: 100px;">交易日期</th>
            <th style="width: 100px;">成交金额</th>
        </tr>
        <c:forEach items="${userLoginAll }" var="u" varStatus="status">
            <tr>
                <td style="width: 100px; text-align: center;">${u.imei }</td>
                <td style="width: 100px; text-align: center;">${u.log_times }</td>
                <td style="width: 100px; text-align: center;">${u.first_login_time }</td>
                <td style="width: 100px; text-align: center;">${u.online_time }</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>