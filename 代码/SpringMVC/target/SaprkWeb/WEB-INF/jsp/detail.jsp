<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/echarts.js" ></script>
    <title>详情</title>
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
    <table class="table">
        <tr>
            <th style="width: 100px;">编号</th>
            <th style="width: 100px;">最高价</th>
            <th style="width: 100px;">交易所详情</th>
            <th style="width: 100px;">交易日期</th>
            <th style="width: 100px;">成交量</th>
        </tr>
        <c:forEach items="${userDetailList }" var="u" varStatus="status">
            <tr>
                <td style="width: 100px; text-align: center;">${u.imei }</td>
                <td style="width: 100px; text-align: center;">${u.requestip }</td>
                <td style="width: 100px; text-align: center;">${u.requesttypename }</td>
                <td style="width: 100px; text-align: center;">${u.first_login_time }</td>
                <td style="width: 100px; text-align: center;">${u.log_times }</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>