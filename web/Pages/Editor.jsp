<%@ page import="com.Util.CONSTANTS" %><%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/31
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = (String)session.getAttribute(CONSTANTS.USER_DATA.USERNAME);
    String avatarType = (String)session.getAttribute(CONSTANTS.USER_DATA.AVATAR_TYPE);
%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>XmYBlog - Editor</title>
    <link rel="stylesheet" href="../extraLib/editormd/css/editormd.css" />
    <link rel="stylesheet" href="../cssLib/editor.css">
    <link rel="stylesheet" href="../cssLib/theme_dark.css">
    <script src="../extraLib/jQuery/jquery.js"></script>
    <script src="../jsLib/CommonFun.js"></script>   <%-- 共同使用的JavaScript函数库 --%>
    <script type="text/javascript" src = ../extraLib/layer/layer.js></script>  <%-- 弹出框库 --%>
    <script src="../extraLib/editormd/js/editormd.min.js"></script>
    <script src="../jsLib/Editor.js"></script>
    <script>
        $(function (){
            servletRootPath = '${pageContext.servletContext.contextPath}'
        })
    </script>
</head>
<body>
<div id="editor-header">
    <div id="article-manager">
        <i class="fa fa-angle-left" aria-hidden="true" style="font-size: larger;"></i>
        <a href="test.jsp">文章管理</a>
    </div>
    <div id="article-title">
        <input id="title-text" type="text" placeholder="请输入文章标题（最多100字）">
    </div>
    <div id="article-biubiubiu">
        <button id="article-save" onclick="saveArticle(true)">保存草稿</button>
        <button id="article-send" onclick="saveArticle(false)">发布文章</button>
        <div id="user-profile">
            <a id="avatar" href="../Home.jsp">
                <img src="<%=pageContext.getServletContext().getContextPath()+CONSTANTS.IMAGE_UPLOAD.RELATIVE_PATH+"/"+username+"."+avatarType%>" alt="找不到了">
            </a>
            <div id="option-box" style="display: none;">
                <ul>
                    <li>我的博客</li>
                    <li>退出</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<form id="editor-form">
    <div id="article-editor" style="margin-bottom: 0;">
        <input type="password" id="form-title" name="title" style="display: none">
        <input type="password" id="form-time" name="create_date_ms" style="display: none">
        <input type="password" id="form-type" name="isDraft" style="display: none">
        <textarea id="form-editor" name="content" style="display:none;"></textarea>
    </div>
</form>
</body>
</html>
