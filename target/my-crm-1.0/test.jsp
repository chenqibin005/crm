<%--
  Created by IntelliJ IDEA.
  User: zzzzh
  Date: 2020/8/31
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
        $.ajax({
        url:"",
        data:{

        },
        type:"post",
        dataType:"json",
        success:function (data) {

        }
        })
</body>
</html>
