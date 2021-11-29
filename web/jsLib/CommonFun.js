function isValidUser(str) {
    return /^\w+$/.test(str);
}

function isEmptyText(selector){
    if($(selector).val() === ""){
        layer.alert($(selector).attr("id") + "不能为空！",{
            title: '警告',
            icon:0,
        });
        return true;
    }
    return false;
}

function serverErrorTips(){
    layer.alert("服务器返回错误!",{
        title:'警告！',
        icon:0
    })
}

function confirmOperation(operation, title, statement, buttonText, iconNumber){
    layer.open({
        title: title,
        content: statement,
        icon : iconNumber,
        shadeClose: true,
        btn: [ buttonText,'关闭'],
        btn1: function(){
            operation()
        },
        btn2: function(){
            layer.close();
        }
    })
}

function loginTips(){
    confirmOperation(
        function () {
            toLogin()
        },
        '温馨提示',
        '这位亲爱的同学，您没有使用这项功能的权限呢。登录后才能使用哦。',
        '登录',
        0
    )
}

/**
 * 对函数进行用户限制
 * @param service
 */
function userFilter(service){   //过滤未登录的用户，一些功能只能登录的用户使用
    if(parseInt(sessionStorage.getItem('userID')) > 0){
        service()
    }else{
        loginTips()
    }
}

function toLogin(){
    location.href = 'Pages/Login.jsp';
}
