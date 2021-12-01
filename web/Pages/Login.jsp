<%@ page import="com.Util.CONSTANTS" %>
<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/8
  Time: 19:09
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <script src="../extraLib/jQuery/jquery.js"></script> <%-- jQuery库 --%>
  <script src="../jsLib/CommonFun.js"></script>   <%-- 共同使用的JavaScript函数库 --%>
  <script type="text/javascript" src = ../extraLib/layer/layer.js></script>  <%-- 弹出框库 --%>
  <title>XmYBLOG 登录</title>
  <link rel="stylesheet" href="../cssLib/login.css">
  <!-- 图标库 -->
  <link rel="stylesheet" href="../extraLib/fontAwesome/css/font-awesome.min.css">
  <link rel="shortcut icon" href="../resource/img/favicon.png" >
</head>
<body>
<div class = "main-part">
  <div class = "img-box">
  </div>
  <div class = "login-box">
    <div id = "login-title">
      <h1>XmYBLOG</h1>
    </div>
    <div id = "login-content">
      <div>
        <i class = "fa fa-user-o"></i>
        <input type="text" id = "username" class = "login-text" placeholder="USERNAME">
      </div>
      <div>
        <i class = "fa fa-keyboard-o"></i>
        <input type="password" id = "password" class = "login-text" placeholder="PASSWORD">
      </div>
      <span>
        <a href="Register.jsp" id = "register">Register!</a>
        <a href="" id = "forget">Forget?</a>
      </span>
    </div>
    <button id = "login-button" onclick="Login_submit()">Login</button>
  </div>
</div>
</body>
<script>
  function Login_submit(){

    let username = $("#username").val();
    if(isEmptyText("#username", '用户名')){ return; }
    let password = $("#password").val();
    if(isEmptyText("#password", '密码')){ return;}

    if(!isValidUser(username)){
      layer.alert('用户名不符合格式！',{
        title: '提示框',
        icon:0,
      });
      return;
    }

    $.ajax({
      url:"Login",
      type:"post",
      dataType:"text",
      data: {
        "<%=CONSTANTS.USER_DATA.USERNAME%>": username,
        "<%=CONSTANTS.LOGIN_DATA.PASSWORD%>": password
      },
      success:function (data) {
        if(data === "<%=CONSTANTS.LOGIN_DATA.LOGIN_SUCCESS%>"){
          location.href = "../Home.jsp";
        }else{
          layer.alert("用户名或密码错误!",{
            title:'提示框',
            icon:0
          })
        }
      },
      error:function () {
        serverErrorTips()
      }
    })
  }
</script>
</html>