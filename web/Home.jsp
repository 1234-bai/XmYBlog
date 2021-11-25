<%@ page import="com.Util.CONSTANTS" %>
<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/15
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    final String admin = "admin";
    String showName = "\u5343\u5C0F\u4E00"; //"千小一"
    String username = (String)session.getAttribute(CONSTANTS.LOGIN_DATA.USERNAME);  //从session获得登录的用户姓名
    if(username == null){
        username = admin;
    } else{
        showName = username;
    }
%>
<html>
<head>
    <meta charset="utf-8" />
    <title>XmYBLOG--<%=showName%></title>
    <script src="extraLib/jQuery/jquery.js"></script>
    <script src = extraLib/layer/layer.js></script>
    <script src="jsLib/CommonFun.js"></script>
    <script src="jsLib/Home.js"></script>
    <link rel="stylesheet" href="extraLib/layer/skin/layer.css">
    <link rel="stylesheet" href="extraLib/fontAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="cssLib/home.css">
    <link rel="stylesheet" href="cssLib/constant.css">
    <link rel="stylesheet" href="cssLib/theme_dark.css">
    <script>
        $(function (){
            sessionStorage.setItem(
                'username',
                <%=session.getAttribute(CONSTANTS.LOGIN_DATA.USERID)%>
            )
        })
    </script>
</head>
<body>
<div id = "top"></div>
<div id = max_div>
    <%@include file="Pages/HomeParts/LeftPart.jsp"%>
    <%@include file="Pages/HomeParts/CenterPart.jsp"%>
    <%@include file="Pages/HomeParts/RightPart.jsp"%>
</div>
<div id = float_bar>
    <div id = theme_transform class = float_button>
        <i class = "fa fa-adjust"></i>
    </div>
    <div id = back_top class = float_button >
        <a href="#top" class = "fa fa-rocket"></a>
    </div>
    <div id = up_array class = float_button onclick="showOtherButton()">
        <i id = "float_angle" class = "fa fa-angle-down"></i>
    </div>
</div>
</body>
</html>