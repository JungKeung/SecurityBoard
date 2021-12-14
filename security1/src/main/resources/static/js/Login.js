document.getElementById("LoginBtn").onclick = function () {
        var UserId = document.getElementById("LoginId").value;
        var UserPwd = document.getElementById("LoginPassword").value;


        if (UserId === null || UserId === '') {
            alert("아이디를 입력하세요");
            return false;
        }
        if (UserPwd === null || UserPwd === '') {
            alert("비밀번호를 입력하세요");
            return false;
        } else {
            return;
        }
    }

