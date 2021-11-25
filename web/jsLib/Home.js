function showOtherButton() {
    if($("#float_angle").attr("class") === "fa fa-angle-down") {
        $("#theme_transform").hide();
        $("#back_top").hide();
        $("#float_angle").attr("class" ,"fa fa-angle-up");
    } else{
        $("#theme_transform").show();
        $("#back_top").show();
        $("#float_angle").attr("class" ,"fa fa-angle-down");
    }
}