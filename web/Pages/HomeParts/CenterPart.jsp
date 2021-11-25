<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/11/13
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="cssLib/Center/center_part.css">
</head>
<body>
<div id = center_part class = outer_div>
    <div id = search_bar>
        <input id=bar_text type="text" placeholder="search users by pressing enter" >
    </div>
    <div id = main_part>
        <div id = profile>
            <div id = profile_banner>
                <div id = profile_signature>
                    <span>我不想做前端！！！啊啊啊啊啊啊！！真的巨难！！！</span>
                </div>
            </div>
            <div id = profile_avatar>
                <a href="">
                    <img src ="resourse/img/adminProfile.jpg" alt="1234">
                </a>
            </div>
            <div id = profile_msg>
                <p>
                    <a href=""><%=showName%></a>
                    <button>关注</button>
                </p>
                <p>
                        <span id = profile_age>
                            入园年龄：
                            <span id = age_during>0年0个月</span>
                        </span>
                    <a href="">
                        粉丝：
                        <span id = num_followers>0</span>
                    </a>
                    <a href="">
                        关注：
                        <span id = "num_follows">0</span>
                    </a>
                </p>
            </div>
            <div id = profile_menu>
                <a target="_blank" href="">相册</a>
                <a target="_blank" href="">文章</a>
            </div>
        </div>
        <div id = articles_container>
            <%@include file="ArticleContent.jsp"%>
        </div>
    </div>
    <div id = copyright_bar>
        Copyright &copy <span id = copyright_year></span> 千小一
        <script>
            $("#copyright_year").text(new Date().getFullYear());
        </script>
    </div>
</div>
</body>
</html>
