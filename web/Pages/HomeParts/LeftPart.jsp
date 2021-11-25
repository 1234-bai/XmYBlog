<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/15
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="cssLib/Left/left_part.css">
</head>
<!--实现悬浮效果，以及缩放的正常形态-->
<body>
    <div id = left_part class = outer_div>
        <div id = left_log class = log>
            <a id = "log_font" href="" target="_blank" >XmYBLOG</a>
        </div>
        <div id = left_menu class = side_wrapper>
            <h3>MENU</h3>
            <ul>
                <a href="Pages/test.jsp" target="_blank">
                    <i class = "fa fa-table" style="color: #1B86F9"></i>文章管理
                </a>
                <a onclick="jumpToEditor()">
                    <script>
                        function jumpToEditor(){
                            if(parseInt(sessionStorage.getItem('username')) >= 0){
                                location.href = 'Pages/Editor.jsp'
                            }else{
                                layer.open({
                                    title: '温馨提示',
                                    content: '亲爱的这位同学，您没有编辑文章的权限呢。登录后才能编辑。',
                                    shadeClose: true,
                                    //area: ['400px', '500px'],
                                    btn: ['登录','关闭'],
                                    btn1: function(){
                                        location.href='Pages/Login.jsp';
                                    },
                                    btn2: function(){
                                        layer.close();
                                    }
                                })
                            }
                        }
                    </script>
                    <i class = "fa fa-pencil-square" style="color: #e7339f"></i>新文章
                </a>
                <a href="" target="_blank">
                    <i class = "fa fa-envelope" style="color: #48c774"></i>日志查看
                </a>
                <a href="" target="_blank">
                    <i class="fa fa-cog" style="color: #00d1b2"></i>账号管理
                </a>
            </ul>
        </div>
        <div id = left_favorite class= "favorite side_wrapper">
            <h3>LINKS</h3>
            <ul>
                <a href="https://www.csdn.net/" target="_blank">
                    CSDN
                </a>
                <a href="https://www.cnblogs.com/" target="_blank">
                    博客园
                </a>
                <a href="https://www.jianshu.com/" target="_blank">
                    简书
                </a>
            </ul>
        </div>
        <div id = left_bottom>
            <a href="https://github.com/1234-bai" target="_blank" id = github_link>
                <span class = github_link_home>
                    <i class = "fa fa-github"></i>Fork Me on Github
                </span>
            </a>
        </div>
    </div>
</body>
<script>
        $(function (){
            sessionStorage.setItem(
                'username',
                <%=session.getAttribute(CONSTANTS.LOGIN_DATA.USERID)%>
            )
        })
</script>
</html>