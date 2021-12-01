<%@ page import="com.Util.CONSTANTS" %><%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/24
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>XmYBlog--注册</title>

    <script src="../extraLib/jQuery/jquery.js"></script> <%-- jQuery库 --%>
    <script type="text/javascript" src = ../extraLib/layer/layer.js></script>
    <script src="../jsLib/CommonFun.js"></script>

    <link rel="stylesheet" href="../cssLib/register.css">
    <link rel="stylesheet" href="../extraLib/layer/skin/layer.css">
    <link rel="shortcut icon" href="../resource/img/favicon.png" >
</head>
<body>
<div class="out-box">
    <div class="register-box">
        <div class="logo-box">
            <h1>
                <span>Sign Up for XmYBLOG</span>
            </h1>
            <div>Welcome To My Blog!</div>
        </div>
        <div class="register-form">
            <div id="user-name">
                <div>Username*</div>
                <input type="text" id="Username">
            </div>
            <div id="pass-word">
                <div id="password-first" class="password-div">
                    <div> Password*</div>
                    <input type="password" id="Password">
                </div>
                <div class="password-div">
                    <div>Retype-Password*</div>
                    <input type="password" id="Retype-Password">
                </div>
            </div>
            <div id="submit">
                <input type="button" value="Register" onclick="submit()">
            </div>
        </div>
        <div class="login-box">
            Already have a account?<a href="Login.jsp">Login!</a>
        </div>
    </div>
    <div class="img-box">
        <div class="login-box">
            Already have a account?<a href="Login.jsp">Login!</a>
        </div>
    </div>
</div>
</body>
<script>
    // 函数太长，提取函数
    function submit() {
        if(isEmptyText("#Username",'用户名')){  return; }
        if(isEmptyText("#Password",'密码')){ return; }
        if(isEmptyText("#Retype-Password",'再次输入密码')){ return; }
        let userName = $("#Username").val();
        let password = $("#Password").val();
        let retype_password = $("#Retype-Password").val();
        if(!isValidUser(userName)){
            layer.alert('用户名不符合格式！',{
                title: '提示框',
                icon:0,
            });
            return;
        }
        if(password !== retype_password){
            layer.alert('两次密码输入不相同',{
                title: '提示框',
                icon:0,
            });
            return;
        }

        $.ajax({
            url:"Register",
            data:{
                "<%=CONSTANTS.REGISTER_DATA.USERNAME%>" : userName,
                "<%=CONSTANTS.REGISTER_DATA.PASSWORD%>" : password
            },
            type:"post",
            dataType:"text",        //返回的数据格式
            success:function (data) {
                if(data === "<%=CONSTANTS.REGISTER_DATA.REGISTER_SUCCESS%>"){
                    layer.open({
                        title: '信息',
                        content: '注册成功！',
                        shadeClose: true,
                        icon : 1 ,
                        //area: ['400px', '500px'],
                        btn: ['登录','关闭'],
                        btn1: function(){
                            location.href='Login.jsp';
                        },
                        btn2: function(){
                            layer.close();
                        }
                    })
                } else {
                    layer.alert('用户已存在！',{
                        title: '提示框',
                        icon:0,
                    });
                }
            },
            error:function () {
                serverErrorTips()
            }
        });
    }
</script>
</html>
