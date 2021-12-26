function search(){
    var url = "http://localhost:8080/web_demo1/passage";
    var username = document.getElementById("search_user").value;
    var title = document.getElementById("search_title").value;
    if (username !== null && username.length !== 0){
        url = url + "?username=" + username
    }
    if (title !== null&& title.length !== 0){
        if (url.includes('?')){
            url = url + "&title=" + title;
        } else {
            url = url + "?title=" + title;
        }
    }
    window.location.href = url;
}

function clear_search(){
    var oUrl = this.location.href.toString();
    window.location.href = oUrl.split('?')[0];
}

function delRow(index){
    if(confirm('确定要删除吗')===true){
        var id =  document.getElementById("no" + index).textContent;
        window.location.href='http://localhost:8080/web_demo1/del_passage?id=' + id.toString();
    }else{
        return null;
    }
}

function editPage(index){
    var id =  document.getElementById("no" + index).textContent;
    window.location.href='http://localhost:8080/web_demo1/main/edit_passage?id=' + id.toString();
}

function managePassage(index){
    var username =  document.getElementById("username" + index).textContent;
    window.location.href='http://localhost:8080/web_demo1/main/manege_passage?username=' + username;
}