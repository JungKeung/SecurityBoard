 function login(){
        var id = document.querySelector('#id');
        var pw = document.querySelector('#pw');

        if(id.value == "" || pw.value ==""){
            alert("로그인 할 수 없습니다.")
        }
        else {
            location.href = 'index.html');
        }
    }