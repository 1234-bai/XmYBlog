<%@ page import="com.Util.CONSTANTS" %>
<%@ page import="com.Entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/15
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String showName =  "qian_xiao_yi";
    String username = (String)session.getAttribute(CONSTANTS.USER_DATA.USERNAME);  //从session获得登录的用户姓名
    boolean hasUser = false;
    Integer userID = -1;
    String nickname = "千小一";
    String avatarType = "jpg";
    if(username != null){   //说明是从登录入口进来的
        hasUser = true;
        showName = username;
        userID = (Integer) session.getAttribute(CONSTANTS.USER_DATA.USERID);
        nickname = (String)session.getAttribute(CONSTANTS.USER_DATA.NICKNAME);
        avatarType = (String)session.getAttribute(CONSTANTS.USER_DATA.AVATAR_TYPE);
    }
    String name = (String)session.getAttribute(CONSTANTS.SHOW_NAME);    //说明在查看别人的界面
    if(name != null){
        showName = name;
    }
    Boolean managingArticle = (Boolean) session.getAttribute(CONSTANTS.ARTICLES_DATA.MANAGE_ARTICLES);
    if(managingArticle == null){
        managingArticle = false;
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
    <script src="jsLib/ImageUpload.js"></script>
    <link rel="stylesheet" href="extraLib/layer/skin/layer.css">
    <link rel="stylesheet" href="extraLib/fontAwesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="cssLib/home.css">
    <link rel="stylesheet" href="cssLib/constant.css">
    <link rel="stylesheet" href="cssLib/theme_dark.css">
    <link rel="shortcut icon" href="resource/img/favicon.png" >
    <script>
        $(function (){  //将数据存入本地对话，方便js使用
            servletRootPath = '${pageContext.servletContext.contextPath}'
            sessionStorage.setItem(
                'userID',
                <%=session.getAttribute(CONSTANTS.USER_DATA.USERID)%>
            )
            sessionStorage.setItem(
                'username',
                '<%=username%>'
            )
            sessionStorage.setItem(
                'nickname',
                '<%=nickname%>'
            )
            sessionStorage.setItem(
                'avatarType',
                '<%=avatarType%>'
            )
            sessionStorage.setItem(
                'showName',
                '<%=showName%>'
            )
        })
    </script>
</head>
<body>
<div id = max_div>
    <%@include file="Pages/HomeParts/LeftPart.jsp"%>
    <%@include file="Pages/HomeParts/CenterPart.jsp"%>
    <%@include file="Pages/HomeParts/RightPart.jsp"%>
</div>
<div id = float_bar>
    <div id = theme_transform class = float_button style="display: none">
        <i class = "fa fa-adjust"></i>
    </div>
    <div id = back_top class = float_button >
        <a class = "fa fa-rocket" onclick="userFilter(function (){changeShowUser('<%=username%>')})"></a>
    </div>
    <div id = up_array class = float_button onclick="showOtherButton()">
        <i id = "float_angle" class = "fa fa-angle-down"></i>
    </div>
</div>
</body>
</html>