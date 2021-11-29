$(function () {
    // 数字同月份和季节的映射
    const monthsTextDict = ["Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
    const monthsSeasonDict = ["winter", "spring", "spring", "spring", "summer", "summer", "summer", "autumn", "autumn",
        "autumn", "winter", "winter"];
    // 获得当前时间
    const todayDate = new Date();
    const month = todayDate.getMonth();
    $("#calender_date_text").text(todayDate.toLocaleDateString());
    $("#date_picture_month").text(monthsTextDict[month]);
    $("#date_picture_day").text(todayDate.getDate().toString());
    // 获取季节图片
    $("#calendar_season_picture").attr("src", "https://guangzan.gitee.io/imagehost/Illustrations/" + monthsSeasonDict[month] + ".svg");
    $("#calender_describe").text(upperFirstLetter(monthsSeasonDict[month]) + " Wonderland");

    //设置悬浮文字
    $("#right_top_menu_hidden_list").hide();
    $("#chart").mouseover(function (){
        $("#right_top_menu_hidden_list").fadeIn("slow");
    });
    $("#chart").mouseout(function (){
        $("#right_top_menu_hidden_list").fadeOut("slow");
    });
});


// 第一个字母大写的函数
function upperFirstLetter(word) {
    return word.substring(0,1).toUpperCase()+word.substring(1);
}

function logout(){
    confirmOperation(
        function () {
            location.href = servletRootPath + "/logout"
        },
        '询问',
        '确认要注销吗？',
        '注销',
        3
    )
}