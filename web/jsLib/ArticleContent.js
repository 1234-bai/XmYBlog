let readingArticleId = -1
let readingArticleIndex = -1
let articleSupported

/**
 * 将点赞按钮的状态取反
 */
function changeSupportButtonStatus(){
    let select
    if(articleSupported[readingArticleIndex]){  //点赞状态，装换为位点赞状态，同时对应文章的点赞量数据减1
        $("#support_button i").attr('class','fa fa-thumbs-o-up')
        select = "#article_card"+readingArticleIndex+" .support-nums"
        $(select).text(parseInt($(select).text())-1)
        select = $("#supportNums")
        $(select).text(parseInt($(select).text())-1)
        articleSupported[readingArticleIndex] = false;
    }else{
        $("#support_button i").attr('class','fa fa-thumbs-up')
        select = "#article_card"+readingArticleIndex+" .support-nums"
        $(select).text(parseInt($(select).text())+1)
        select = $("#supportNums")
        $(select).text(parseInt($(select).text())+1)
        articleSupported[readingArticleIndex] = true;
    }
}

/**
 * 从阅览区返回
 */
function backToArticlesCards() {
    $("#articles_container_cardList").show();   //展示文章卡片
    $("#articles-content").hide();  //隐藏文章内容
    $("#comment-block").hide(); //隐藏评论区
    $("#support_button i").attr('class','fa fa-thumbs-o-up');   //"点赞键"设置为空心状态
    $("#comment-button i").attr('class', 'fa fa-commenting-o')
    readingArticleIndex = readingArticleId = -1;    //没有正在阅读的文章
    let read_content = $("#read_content");
    read_content.empty(); //删除文章展示框子元素
    read_content.append("<textarea  id=\"article-show\" style=\"display: none\"></textarea>");    //添加预览区域
}

/**
 * 点击点赞按钮时的动作
 */
function supportArticle() {
    if(readingArticleId === -1){    //直接返回
        return
    }
    //获得此文章点赞的状态
    let num_change_type;    //将要进行的操作时增加还是减少
    let supported = articleSupported[readingArticleIndex];  //原来的状态，是否点过赞
    if(supported){   //点过赞
        num_change_type = 'decrease'
    }else{          //没有点赞
        num_change_type = 'increase'
    }

    //ajax语句。
    $.ajax({
        data:{
            'articleID'  : readingArticleId,
            'num_change_type' : num_change_type
        },
        dataType:'text',
        type:'post',
        url: servletRootPath+'/supportArticle',
        success:function (msg) {
            if(msg === 'fail'){
                layer.alert("点赞失败！",{
                    title:'信息',
                    icon:0
                })
            } else{ //成功则将改变点赞按钮的状态
                changeSupportButtonStatus(supported)
            }
        },
        error:function () {
            serverErrorTips()
        }
    })
}

/**
 * 文章成功返回时执行的动作
 */
function readArticleSuccess(msg,artIndex, articleID) {
    //成功获取到文章内容，进入文章阅览区
    $("#ownerName").text(msg.ownerName)
    $("#createDate").text(msg.createDate)
    $("#lastEditDate").text(msg.lastChangeDate)
    $("#clickNums").text(msg.clickNums)
    $("#supportNums").text(msg.supportNums)
    $("#commentNums").text(msg.commentNums)
    $("#article-show").val(msg.content)   //阅览区加入内容

    editormd.markdownToHTML("read_content")  //将md语句转化为HTML
    $("#articles_container_cardList").hide()    //隐藏卡片
    $("#articles-content").show()   //展示阅览区
    readingArticleId = articleID; readingArticleIndex = artIndex
    sessionStorage.setItem("articleID", articleID);
    //展示点赞状态
    if(articleSupported[readingArticleIndex]){  //如果已经点过赞，则为实心状态
        $("#support_button i").attr('class','fa fa-thumbs-up')
    }else{
        $("#support_button i").attr('class','fa fa-thumbs-o-up')
    }
    //修改点赞量数值
    let select = $("#article_card"+artIndex+" .click-nums")   //点击量加1
    select.text(parseInt(select.text())+1)
}
/**
 * 当在文章阅览卡上点击文章的题目或者阅读按键时，执行
 * 负责展示选择文章内容
 * @param servletURL 服务器URL
 * @param artIndex 在文章卡片组的序号，便于修改卡片的阅览数等数据。
 * @param articleID 在数据库文件中的编号
 */
function showArticle(artIndex, articleID) {
    $.ajax({
        data:{
            'articleID'  : articleID
        },
        dataType:'json',
        type:'post',
        url: servletRootPath + '/readArticle',
        success:function (msg) {
            if(msg.fail === true){
                layer.alert("查不到这个文章！",{
                    title:'信息',
                    icon:0
                })
                return
            }
            readArticleSuccess(msg, artIndex, articleID)
        },
        error:function () {
            serverErrorTips()
        }
    })
}

function loadCommentInf(msg){
    msg = JSON.parse(msg)
    for(let i = 0; i < msg.length ; ++i){
        let html = (
            '      <div class="comment-card">\n' +
            '            <div class="comment-card-title">\n' +
            '                <div class="card-title-level">#'+(i+1)+'楼</div>\n' +
            '                <div class="card-title-username">'+msg[i].owner+'</div>\n' +
            '                <div class="card-title-date">'+msg[i].createDate+'</div>\n' +
            '            </div>\n' +
            '            <div class="comment-card-content">\n' +
            '                <div class="card-avatar" onclick="changeShowUser(\''+msg[i].owner+'\')">\n' +
            '                    <img src="'+servletRootPath+'/resource/img/userAvatar/'+msg[i].owner+'.jpg" alt="sorry, the picture failed">\n' +
            '                </div>\n' +
            '                <div class="card-content">'+msg[i].content+'</div>\n' +
            '            </div>\n' +
            '        </div>'
        )
        $("#comment-card-list").append(html);
    }
}

function loadCommentBlockEditor() {
    editormd("comment-editor",{  //加载评论区编辑框
        height : 230,
        syncScrolling : "single",
        theme : "dark",
        previewTheme : 'dark',
        editorTheme : 'pastel-on-dark',
        watch : false,
        toolbarIcons  : "mini",
        path   : "extraLib/editormd/lib/",
        placeholder :  '最多200字符，空白符也算哦!',
        saveHTMLToTextarea: true
    })
}

/**
 * 展示评论区
 * @param servletURL
 */
function showCommentBlock() {
    $("#comment-block").show()
    $("#comment-button i").attr('class', 'fa fa-commenting')
    $.ajax({    //加载评论区展示列表
        data : {
            'articleID' : readingArticleId
        },
        datatype : 'json' ,
        type : 'post',
        url : servletRootPath+'/readComments',
        success:function (msg){
            $("#comment-card-list").empty()
            if(msg.empty === true){return}
            loadCommentInf(msg)
        },
        error: function (){
            serverErrorTips()
        }
    })
    loadCommentBlockEditor()
}

function deleteArticleCore(articleIndex, articleID){
    $.ajax({
        data : {
            'articleID' : articleID
        },
        dataType : 'text',
        type : 'post',
        url : servletRootPath + '/deleteArticle',
        success : function (msg){
            if(msg === 'success'){
                layer.alert("删除成功！",{
                    title : '文章删除',
                    icon : 1,
                })
                $("#article_card"+articleIndex).remove()
            } else{
                layer.alert("删除失败！",{
                    title : '文章删除',
                    icon : 0
                })
            }
        },
        error : function () {
            serverErrorTips()
        }
    })
}
/**
 * 删除某篇文章
 */
function deleteArticle(articleIndex, articleID) {
    userFilter(function () {
        if(sessionStorage.getItem('username') === sessionStorage.getItem('showName')){
            confirmOperation(
                function (){
                    deleteArticleCore(articleIndex, articleID)
                },
                '温馨提示',
                '确认要删除吗',
                '删除',
                3
            )
        } else{
            layer.alert('这位同学，不要擅自删除他人的文章哦~')
        }
    })
}

/**
 * 编辑某篇文章
 */
function editArticleCore(articleID) {
    $.ajax({
        data : {
            'articleID' : articleID,
        },
        dataType : 'json',
        type : 'post',
        url : servletRootPath + '/readArticle',
        success : function (msg){
            sessionStorage.setItem('article-id', articleID)
            sessionStorage.setItem('article-title', msg.title)
            sessionStorage.setItem('article-content', msg.content)
            window.open(servletRootPath+'/Pages/Editor.jsp', '_blank')
        },
        error : function () {
            serverErrorTips()
        }
    })
}

function editArticle(articleID) {
    userFilter(function () {
        if(sessionStorage.getItem('username') === sessionStorage.getItem('showName')){
            editArticleCore(articleID)
        } else{
            layer.alert('这位同学，不要擅自编辑他人的文章哦~')
        }
    })
}