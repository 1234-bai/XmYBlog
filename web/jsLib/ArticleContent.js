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
    readingArticleIndex = readingArticleId = -1;    //没有正在阅读的文章
    let read_content = $("#read_content");
    read_content.empty(); //删除文章展示框子元素
    read_content.append("<textarea  id=\"article-show\" style=\"display: none\"></textarea>");    //添加预览区域
}

/**
 * 点击点赞按钮时的动作
 */
function supportArticle(servletURL) {
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
        url: servletURL,
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
 * 当在文章阅览卡上点击文章的题目或者阅读按键时，执行
 * 负责展示选择文章内容
 * @param servletURL 服务器URL
 * @param artIndex 在文章卡片组的序号，便于修改卡片的阅览数等数据。
 * @param articleID 在数据库文件中的编号
 */
function showArticle(servletURL, artIndex, articleID) {
    $.ajax({
        data:{
            'articleID'  : articleID
        },
        dataType:'json',
        type:'post',
        url: servletURL,
        success:function (msg) {
            if(msg.fail === true){
                layer.alert("查不到这个文章！",{
                    title:'信息',
                    icon:0
                })
                return
            }
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
        },
        error:function () {
            serverErrorTips()
        }
    })
}

function showCommentBlock(servletURL) {
    $("#comment-block").show()
    $("#comment-button i").attr('class', 'fa fa-commenting')
    $.ajax({    //加载评论区展示列表
        data : {
            'articleID' : readingArticleId
        },
        datatype : 'json' ,
        type : 'post',
        url : servletURL,
        success:function (msg){
            let select = $("#comment-card-list")
            select.empty()
            if(msg.empty === true){return}
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
                    '                <div class="card-avatar">\n' +
                    '                    <img src="'+servletRootPath+'/WEB-INF/lib/upload/img/userAvatar/'+msg[i].owner+'.jpg" alt="sorry, the picture failed">\n' +
                    '                </div>\n' +
                    '                <div class="card-content">'+msg[i].content+'</div>\n' +
                    '            </div>\n' +
                    '        </div>'
                )
                select.append(html);
            }
        },
        error: function (){
            serverErrorTips()
        }
    })
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
