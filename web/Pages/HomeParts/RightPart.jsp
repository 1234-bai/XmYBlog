<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/21
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="cssLib/Right/right_part.css">
    <script src="jsLib/RightPart.js"></script>
</head>
<body>
<div id = right_part class = outer_div>
    <div id = right_top_menu>
        <div class = right_top_menu_button>
            <a href="" target="_blank" >
                <i class = "fa fa-envelope-o"></i>
            </a>
        </div>
        <div class = right_top_menu_button>
            <a href="" target="_blank">
                <i class="fa fa-bell-o"></i>
            </a>
        </div>
        <div class = right_top_menu_button>
            <i id = "chart" class="fa fa-bar-chart"></i>
        </div>
        <div id = right_top_menu_photo>
            <a onclick="<%= hasUser ? "showHideList()" : "toLogin()"%>" >
                <%
                    out.write(hasUser ? "<img src=\""+pageContext.getServletContext().getContextPath()+CONSTANTS.IMAGE_UPLOAD.RELATIVE_PATH+"/"+ username +"."+avatarType+"\">" : "<span>登录/注册</span>");
                %>
            </a>
            <ul id = right_top_menu_hidden_list>
                <li onclick="changeShowUser('<%=username%>')">
                    <i class="fa fa-home" style="color: #1B86F9"></i>
                    主页
                </li>
                <li onclick="logout()">
                    <i class="fa fa-sign-out" style="color: red"></i>
                    登出
                </li>
            </ul>
        </div>
    </div>
    <div id = right_calendar>
        <div id = calendar_wrapper>
            <!--获取当前时间，并在页面显示-->
            <img id = calendar_season_picture>
            <div id = calender_describe></div>
            <div id = calender_date_text></div>
            <div id = calender_date_picture>
                <div id = date_picture_month></div>
                <div id = date_picture_day></div>
            </div>
        </div>
    </div>
    <div id = right_info>
        <div id = info_links>
            <h3>常用链接</h3>
            <ul>
                <li onclick="userFilter(function (){changeShowUser('<%=username%>')})">我的文章</li>
                <li onclick="function x() {location.href = 'test.jsp'}">我的收藏</li>
                <li onclick="function x() {location.href = 'test.jsp'}">我的评论</li>
            </ul>
        </div>
        <div id = info_comments>
            <h3>最新留言</h3>
            <ul>
<%--                存一个留言的表--%>
                <ul class = "whole_comment">
                    <li class = "comment_title">
                        <a href=""></a>
                    </li>
                    <li class = "comment_content">
                        <a href=""></a>
                    </li>
                    <li class = comment_author>
                        <a href=""></a>
                    </li>
                </ul>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
