

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