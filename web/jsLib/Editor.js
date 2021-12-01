let servletRootPath

$(function() {
    editormd("article-editor", {    //加载编辑区
        autoHeight: true,
        syncScrolling : "single",
        theme : "dark",
        previewTheme : "dark",
        editorTheme : 'pastel-on-dark',
        emoji : "true",
        path    : "../extraLib/editormd/lib/",
        saveHTMLToTextarea : true
    });

    let title = sessionStorage.getItem('article-title');
    if(title != null){
        $("#title-text").val(title)
        $("#form-editor").val(sessionStorage.getItem('article-content'))
        $("#article-save").attr('onclick', 'updateArticle(true)')   //修改保存草稿按钮按钮的点击函数
        $("#article-send").attr('onclick', 'updateArticle(false)')
    }
});

function submitArticle(servletPath, isDraft){
    if(isEmptyText("#title-text", '文章题目')){return;}
    $("#form-title").val($("#title-text").val())
    $("#form-time").val(new Date().getTime())
    $("#form-type").val(isDraft.toString())
    $.ajax({
        data: $("#editor-form").serialize(),
        dataType:'text',
        type:'post',
        url: servletPath,
        success:function (msg) {
            if(msg === "success"){
                layer.open({
                    title: '信息',
                    content: '发布成功',
                    icon : 1,
                    btn: ['确认','关闭'],
                    btn1: function(){
                        location.href = '../Home.jsp'
                    },
                    btn2: function(){
                        layer.close();
                        location.href = 'Editor.jsp'
                    }
                })
            } else{
                layer.alert("保存失败！",{
                    title:'信息',
                    icon:0
                })
            }

        },
        error:function () {
            serverErrorTips()
        }
    })
}

function saveArticle(isDraft) {
   submitArticle(servletRootPath+'/saveArticle',isDraft)
}

function updateArticle(isDraft){
    let html = '<input type="password" id="form-id" name="articleID" style="display: none">';
    $("#article-editor").prepend(html)
    $("#form-id").val(sessionStorage.getItem('article-id'))
    submitArticle(servletRootPath + '/updateArticle',isDraft)
}

function backToManage(){
    sessionStorage.removeItem('article-id')
    sessionStorage.removeItem('article-title')
    sessionStorage.removeItem('article-content')
    location.href = servletRootPath+'/manageArticles'
}

function backToHome(){
    sessionStorage.removeItem('article-id')
    sessionStorage.removeItem('article-title')
    sessionStorage.removeItem('article-content')
    location.href = servletRootPath + '/Home.jsp'
}