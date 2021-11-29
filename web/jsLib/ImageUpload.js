function openImageUploadPage(){
    layer.open({
        title : '图像上传',
        content : (
            '       <div class="priview-image" style="display: flex;flex-direction: column;justify-content: space-around;align-items: center;">\n' +
            '           <div>预览</div>\n' +
            '           <ul style="list-style: none;margin: 10px 0 0 10px;padding: 0;">\n' +
            '               <li style="display: flex;flex-direction: column;justify-content: center;align-items: center; margin-bottom: 10px;">\n' +
            '                   <img src="resource/img/empty.png" id="big-pic" style="height: 100px;width: 100px;border-radius: 100%; border: 1px solid gray;">\n' +
            '                   <span style="color: gray; font-size: 12px;">100 x 100</span>\n' +
            '               </li>\n' +
            '               <li style="display: flex;flex-direction: column;justify-content: center;align-items: center;">\n' +
            '                   <img src="resource/img/empty.png" id="little-pic" style="height: 50px;width: 50px;border-radius: 100%; border: 1px solid gray;">\n' +
            '                   <span style="color: gray; font-size: 12px;">50 x 50</span>\n' +
            '               </li>\n' +
            '           </ul>\n' +
            '            <div style="display: flex;flex-direction: row;margin-top: 30px;">\n' +
            '                <button id="upload" onclick="clickUpload()" ' +
            '                                   style="margin-right: 15px;' +
            '                                           background-color: var(--themeColor);\n' +
            '                                           border: 1px solid var(--themeColor);\n' +
            '                                           box-shadow: 0 2px 4px var(--theme-primary-4);\n' +
            '                                           color: #fff;\n' +
            '                                           border-radius: 4px;\n' +
            '                                           cursor: pointer;"' +
            '               >' +
            '                   上传' +
            '               </button>\n' +
            '               <button onclick="submitAvatar()" ' +
            '                       style="background-color: var(--themeColor);\n' +
            '                               border: 1px solid var(--themeColor);\n' +
            '                               box-shadow: 0 2px 4px var(--theme-primary-4);\n' +
            '                               color: #fff;\n' +
            '                               border-radius: 4px;\n' +
            '                               cursor: pointer;"' +
            '               >' +
            '                   确认' +
            '               </button>\n' +
            '            </div>\n' +
            '            <form id="image-form" style="display: none;">\n' +
            '                <input type="file" id="userAvatar" onchange="choseImageFile()">\n' +
            '            </form>\n' +
            '       </div>\n'
        ),
        shadeClose: true,
        area: ['300px', '400px'],
        btn : false
    })
}


function clickUpload() {
    $("#userAvatar").click()
}


function choseImageFile(){
    const file = $("#userAvatar")[0].files[0]
    if(!imageFilter(file)) return
    const src = getObjectURL(file);
    $("#big-pic").attr('src', src)
    $("#little-pic").attr('src', src)
}

function imageFilter(file){
    const imgSize = file.size;
    if(imgSize>1024 * 1024){//1M
        layer.alert("上传图片不能超过1M",{
            title:'警告！',
            icon:0
        })
        return false
    }
    const type = file.type
    if(type !== 'image/jpeg'){
        layer.alert("图片上传格式不正确，目前只支持JPG格式",{
            title:'警告！',
            icon:0
        })
        return false
    }
    return true
}

function getObjectURL(file) {   //获得本地上传的文件路径
    let url = null;
    if (window.createObjectURL!==undefined) { // basic
        url = window.createObjectURL(file) ;
    } else if (window.URL!==undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!==undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}

function submitAvatar(){
    const formData = new FormData();
    formData.append('file', $('#userAvatar')[0].files[0]);
    $.ajax({
        url : servletRootPath + "/imageUpload",
        type : "POST",
        data: formData,
        dataType : 'json',
        cache: false,
        contentType: false,
        processData: false,
        success : function(data) {
            if(data.success === true){
                alert("上传成功！")
            }
        },
        error : function(data) {
            serverErrorTips()
        }
    });
}