let servletRootPath;    //项目访问servlet的根路径

function showOtherButton() {
    if($("#float_angle").attr("class") === "fa fa-angle-down") {
        // $("#theme_transform").hide();
        $("#back_top").hide();
        $("#float_angle").attr("class" ,"fa fa-angle-up");
    } else{
        // $("#theme_transform").show();
        $("#back_top").show();
        $("#float_angle").attr("class" ,"fa fa-angle-down");
    }
}

function changeShowUser(showName){
    $.ajax({
        data : {
            'showName' : showName
        },
        dateType : 'text',
        type : 'post',
        url : servletRootPath + '/jump',
        success :function (msg){
            if(msg === 'success'){
                sessionStorage.setItem('showName', showName)
                location.href = 'Home.jsp'
            } else{
                layer.alert("查无此人！", {
                    icon : 0
                })
            }
        }
    })
}