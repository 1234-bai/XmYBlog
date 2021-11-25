<%@ page import="com.Util.CONSTANTS" %><%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/21
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username_right = (String)session.getAttribute(CONSTANTS.LOGIN_DATA.USERNAME);
    boolean hasUser = (username_right != null);
%>
<script>
    $(function () {
        // 数字同月份和季节的映射
        const monthsTextDict = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
        const monthsSeasonDict = ["winter", "spring", "spring", "spring", "summer", "summer", "summer", "autumn", "autumn",
            "autumn", "winter", "winter"];
        // 获得当前时间
        const todayDate = new Date();
        const month = todayDate.getMonth();
        $("#calender_date_text").text(todayDate.toLocaleDateString());
        $("#date_picture_month").text(monthsTextDict[month]);
        $("#date_picture_day").text(todayDate.getDate().toString());
        // 获取季节图片
        $("#calendar_season_picture").attr("src", "https://guangzan.gitee.io/imagehost/Illustrations/" + monthsSeasonDict[month] + ".svg");
        $("#calender_describe").text(upperFirstLetter(monthsSeasonDict[month]) + " Wonderland");

        //设置悬浮文字
        $("#right_top_menu_hidden_list").hide();
        $("#chart").mouseover(function (){
            $("#right_top_menu_hidden_list").fadeIn("slow");
        });
        $("#chart").mouseout(function (){
            $("#right_top_menu_hidden_list").fadeOut("slow");
        });

        // 第一个字母大写的函数
        function upperFirstLetter(word) {
            return word.substring(0,1).toUpperCase()+word.substring(1);
        }
    });
</script>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="cssLib/Right/right_part.css">
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
            <ul id = right_top_menu_hidden_list>
                <li>随笔:
                    <span>0</span>
                </li>
                <li>阅读:
                    <span>0</span>
                </li>
            </ul>
        </div>
        <div id = right_top_menu_photo>
            <a href= <%= hasUser ? "Home.jsp" : "Pages/Login.jsp" %> >
                <%
                    out.write(hasUser ? "<img src=\"resourse/img/adminProfile.jpg\">" : "<span>登录/注册</span>");
                %>
            </a>
        </div>
    </div>
    <div id = right_calendar>
        <div id = calendar_wrapper>
            <!--获取当前时间，并在页面显示-->
            <img id = calendar_season_picture alt="十分抱歉，图片找不到了" src="">
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
                <li>我的文章</li>
                <li>我的收藏</li>
                <li>我的评论</li>
                <li>我的参与</li>
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
