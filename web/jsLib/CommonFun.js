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

