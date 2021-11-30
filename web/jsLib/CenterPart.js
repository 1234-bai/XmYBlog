$(function(){
    $("#bar_text").keydown(function(event){
        if(event.which === 13){
            changeShowUser($("#bar_text").val())
        }
    });
    $("#profile_banner").css('background-image', 'url("resource/img/background/'+sessionStorage.getItem('showName')+'.png")')
});
