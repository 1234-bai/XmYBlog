<%@ page import="com.Entity.Article" %>
<%@ page import="com.Service.ArticleService" %>
<%--
  Created by IntelliJ IDEA.
  User: QianXiaoYi
  Date: 2021/11/13
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //username在进入home界面时获得。
    Article[] articles = new ArticleService().getPersonArticles(showName);
    boolean haveArticles = (articles != null);
%>
<html>
<head>
    <link rel="stylesheet" href="extraLib/editormd/css/editormd.preview.css" />
    <script src="extraLib/editormd/js/editormd.min.js"></script>
    <script src="extraLib/editormd/lib/marked.min.js"></script>
    <script src="extraLib/editormd/lib/prettify.min.js"></script>
    <script src="jsLib/ArticleContent.js"></script>
    <link rel="stylesheet" href="cssLib/Center/article_content.css" />
    <script>
        $(function (){
            articleSupported = [
                <%
                    if(haveArticles){   //有用户有文章
                        for(int i = 0; i < articles.length - 1; ++i){out.write("false,");}
                        out.write("false");
                    }
                %>
            ]
        })
    </script>
</head>
<body>
    <div id="articles_container_cardList">
      <%
        for (int artIndex = 0; haveArticles && artIndex < articles.length; artIndex++) {
            Article article = articles[artIndex];
            if(article.isDraft() && !managingArticle){continue;}
            long articleID = article.getArticleId();
      %>
      <div id="<%="article_card"+artIndex%>" class = article_card>
        <a class = article_title onclick="showArticle(<%=artIndex%>,<%=articleID%>)">
          <i class = "fa fa-file-text-o" style="margin-right: 8px; font-weight: 500"></i>
          <span>
            <%=article.getTitle()%>
          </span>
            <span style="color: red"><%=(managingArticle && article.isDraft() ? "（草稿）" : "")%></span>
        </a>
        <span class=article_content>
          <div class="content_text">
              <B>摘要：</B>
              <%=article.getSummary()%>
          </div>
        </span>
        <div class = article_buttons>
          <div>
            <i class = "fa fa-eye"></i>
            <span class="click-nums">
                <%=article.getClickNums()%>
            </span>
          </div>
          <div>
            <i class = "fa fa-commenting-o"></i>
            <span class="comment-nums">
              <%=article.getCommentNums()%>
            </span>
          </div>
          <div>
            <i class="fa fa-thumbs-o-up"></i>
            <span class="support-nums">
              <%=article.getSupportNums()%>
            </span>
          </div>
          <button onclick="editArticle(<%=articleID%>)">编辑</button>
          <button onclick="deleteArticle(<%=artIndex%>,<%=articleID%>)">删除</button>
        </div>
      </div>
      <%
        }
      %>
    </div>
    <div id="articles-content" style="display: none">
        <div id="read_content">
              <textarea  id="article-show" style="display: none"></textarea>
        </div>
        <div id="read_button_block">
            <button id="exit_button" class="read_button" onclick="userFilter(function (){editArticle(readingArticleId)})">
                <i class="fa fa-pencil"></i>
                编辑
            </button>
            <button id="support_button" class="read_button" onclick="supportArticle()">
                <i class="fa fa-thumbs-o-up"></i>
                点赞
            </button>
            <button id="comment-button" class="read_button" onclick="userFilter(function (){showCommentBlock()})">
                <i class="fa fa-commenting-o"></i>
                评论
            </button>
            <button id="back_button" class="read_button" onclick="backToArticlesCards()">
                <i class="fa fa-backward"></i>
                返回
            </button>
        </div>
        <div id="read_inf_block">
            <div class="read-inf-outer-div">
                Post @
                <span id="ownerName"></span>
            </div>
            <div class="read-inf-outer-div">
                创建时间：
                <span id="createDate"></span>
            </div>
            <div class="read-inf-outer-div">
                最后编辑时间：
                <span id="lastEditDate"></span>
            </div>
            <div class="read-inf-outer-div" id="read-nums-block">
                <div>
                    阅读
                    (<span id="clickNums"></span>)
                </div>
                <div>
                    赞同
                    (<span id="supportNums"></span>)
                </div>
                <div>
                    评论
                    (<span id="commentNums"></span>)
                </div>
            </div>
        </div>
        <div id="comment-block" style="display: none">
            <%@include file="ArticleParts/Comment.jsp"%>
        </div>
    </div>
</body>
</html>
