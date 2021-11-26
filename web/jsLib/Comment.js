function submitComment(servletURL, articleID) {
    $("#articleID").val(articleID)
    $("#createTime_ms").val(new Date().getTime())
    $.ajax({
        data : $("#comment-editor").serialize(),
        dataType : 'text',
        type : 'post',
        url : servletURL,
        success:function (msg) {
            if(msg === 'success'){
                layer.msg("发表成功！",{
                    title : '信息',
                    icon : 1
                })
                $("#comment-button").click()
                let select = $("#commentNums");
                select.text(parseInt(select.text())+1)
                select = $("#article_card"+readingArticleIndex+" .comment-nums")   //点击量加1
                select.text(parseInt(select.text())+1)
            } else{
                layer.alert("保存失败！也许是文本太长了呢！",{
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