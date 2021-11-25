let readingArticleId = -1
let readingArticleIndex = -1
let articleSupported

/**
 * 将点赞按钮的状态取反
 */
function changeSupportButtonStatus(){
    let select = "#article_card"+readingArticleIndex+" .support-nums"
    if(articleSupported[readingArticleIndex]){  //点赞状态，装换为位点赞状态，同时对应文章的点赞量数据减1
        $("#support_button i").attr('class','fa fa-thumbs-o-up')
        $(select).text(parseInt($(select).text())-1)
        articleSupported[readingArticleIndex] = false;
    }else{
        $("#support_button i").attr('class','fa fa-thumbs-up')
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
            layer.alert("服务器返回错误!",{
                title:'警告！',
                icon:0
            })
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
        dataType:'text',
        type:'post',
        url: servletURL,
        success:function (msg) {
            if(msg === 'fail'){
                layer.alert("查不到这个文章！",{
                    title:'信息',
                    icon:0
                })
            } else{ //成功获取到文章内容，进入文章阅览区
                $("#article-show").val(msg)   //阅览区加入内容
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
                let select = "#article_card"+artIndex+" .click-nums"   //点击量加1
                $(select).text(parseInt($(select).text())+1)
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

function showCommentBlock() {
    $("#comment-block").show()
    $("#comment-button i").attr('class', 'fa fa-commenting')
    editormd("comment-editor",{ //加载评论编辑区
        height : 230,
        syncScrolling : "single",
        theme : "dark",
        previewTheme : 'dark',
        editorTheme : 'pastel-on-dark',
        watch : false,
        toolbarIcons  : "mini",
        path   : "extraLib/editormd/lib/",
        placeholder :  '支持Markdown!',
        saveHTMLToTextarea: true
    })
}
