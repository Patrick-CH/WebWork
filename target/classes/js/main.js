function Hide(nid) {
    <!-- 获取当前鼠标点击的id -->
    var current_header= document.getElementById(nid);
    current_header.nextElementSibling.classList.add('hide')
}
function loadjscssfile(filename,filetype){
    if(filetype == "js"){
        var fileref = document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src",filename);
    }else if(filetype == "css"){

        var fileref = document.createElement('link');
        fileref.setAttribute("rel","stylesheet");
        fileref.setAttribute("type","text/css");
        fileref.setAttribute("href",filename);
    }
    if(typeof fileref != "undefined"){
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }
}

function delRow(index){
    var userName =  document.getElementById("username" + index).textContent;
    window.location.href='http://localhost:8080/web_demo1/del_user?username=' + userName;
}

function search(){
    var text = document.getElementById("search").value;
    window.location.href="http://localhost:8080/web_demo1/main?search=" + text;
}

function editPage(index){
    var userName =  document.getElementById("username" + index).textContent;
    window.location.href='http://localhost:8080/web_demo1/main/change_other?other=' + userName;
}