<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/11/24
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="extraLib/editormd/css/editormd.css" />
    <link rel="stylesheet" href="cssLib/Center/article_comment.css">
    <script src="extraLib/editormd/js/editormd.min.js"></script>
    <script src="jsLib/ArticleComment.js"></script>
</head>
<body>
<div id="comment-form">
    <div id="comment-title">
        <i class="fa fa-commenting-o"></i>
        发表评论
    </div>
    <form id="comment-editor"  method="get" action="${pageContext.servletContext.contextPath}/submitComment">
        <!-- Tips: Editor.md can auto append a `<textarea>` tag -->
        <textarea style="display:none;"></textarea>
        <input type="password" style="display: none" id="articleID" name="articleID">
        <input type="password" style="display: none" id="createTime_ms" name="createTime_ms">
    </form>
    <div id="comment-submit-button">
        <button onclick="userFilter(function(){
                submitComment('${pageContext.servletContext.contextPath}/submitComment',readingArticleId)
        })">
            提交评论
        </button>
    </div>
</div>
</body>
</html>
