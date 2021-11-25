<%@ page import="com.Util.CONSTANTS" %><%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/10/31
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Simple example - Editor.md examples</title>
    <link rel="stylesheet" href="../cssLib/editor.css">
    <link rel="stylesheet" href="../extraLib/editormd/css/editormd.css" />
    <script src="../extraLib/jQuery/jquery.js"></script>
    <script src="../jsLib/CommonFun.js"></script>   <%-- 共同使用的JavaScript函数库 --%>
    <script type="text/javascript" src = ../extraLib/layer/layer.js></script>  <%-- 弹出框库 --%>
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
        <button id="article-save" onclick="save_draft()">保存草稿</button>
        <button id="article-send" onclick="send_article()">发布文章</button>
        <div id="user-profile">
            <a id="aviator" href="">
                <img src="" alt="找不到了">
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
<div id="article-editor" style="margin-bottom: 0;">
    <textarea id="editor" style="display:none;">
# 博客文章测试
#### Disabled options

- TeX (Based on KaTeX);
- Emoji;
- Task lists;
- HTML tags decode;
- Flowchart and Sequence Diagram;

#### Editor.md directory

    editor.md/
            lib/
            css/
            scss/
            tests/
            fonts/
            images/
            plugins/
            examples/
            languages/
            editormd.js
            ...

```html
        &lt;!-- English --&gt;
        &lt;script src="../dist/js/languages/en.js"&gt;&lt;/script&gt;

        &lt;!-- 繁體中文 --&gt;
        &lt;script src="../dist/js/languages/zh-tw.js"&gt;&lt;/script&gt;
        ```
    </textarea>
</div>
</form>
<script src="../extraLib/editormd/js/editormd.min.js"></script>
<script type="text/javascript">
    $(function() {
        editormd("article-editor", {
            autoHeight: true,
            syncScrolling : "single",
            theme : "dark",
            previewTheme : "dark",
            editorTheme : 'pastel-on-dark',
            emoji : "true",
            path    : "../extraLib/editormd/lib/",
           // saveHTMLToTextarea : true
            // 保存 HTML 到 Textarea，true表示转化为html格式的内容也同样提交到后台。Textarea name = 放置textarea的div的id + "-html-code"
        });
    });
    
    function save_draft() {
        if(isEmptyText("#title-text")){return;}
        $.ajax({
            data:{
                '<%=CONSTANTS.ARTICLES_DATA.TITLE%>'  : $("#title-text").val(),
                '<%=CONSTANTS.ARTICLES_DATA.CONTENT%>': $("#editor").val(),
                '<%=CONSTANTS.ARTICLES_DATA.CREATE_DATE_MS%>' : new Date().getTime()
            },
            dataType:'text',
            type:'post',
            url:'${pageContext.request.contextPath}/saveArticle',
            success:function (msg) {
                if(msg === "<%=CONSTANTS.ARTICLES_DATA.SUCCESS%>"){
                    layer.alert("保存成功！",{
                        title:'信息',
                        icon:1
                    })
                    location.href = 'home.jsp'
                } else{
                    layer.alert("保存失败！",{
                        title:'信息',
                        icon:0
                    })
                }

            },
            error:function () {
                layer.alert("服务器返回错误!",{
                    title:'警告！',
                    icon:0
                })
            }
        })
    }
</script>
</body>
</html>
