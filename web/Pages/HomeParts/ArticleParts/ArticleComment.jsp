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
</head>
<body>
<div id="comment-form">
    <div id="comment-title">
        <i class="fa fa-commenting-o"></i>
        发表评论
    </div>
    <div id="comment-editor">
        <!-- Tips: Editor.md can auto append a `<textarea>` tag -->
        <textarea style="display:none;"></textarea>
    </div>
    <div id="comment-submit-button">
        <button>提交评论</button>
    </div>
</div>
</body>
<script>
    $(function (){
        editormd("comment-editor",{
            height : 230,
            syncScrolling : "single",
            theme : "dark",
            previewTheme : 'dark',
            editorTheme : 'pastel-on-dark',
            watch : false,
            toolbarIcons  : "mini",
            path   : "extraLib/editormd/lib/",
            placeholder :  '支持Markdown!'
        })
    })
</script>
</html>
