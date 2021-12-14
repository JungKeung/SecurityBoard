document.getElementById("JoinBtn").onclick = function () {
        var UserId = document.getElementById("UserEmail").value;
        var UserPwd = document.getElementById("UserPw").value;
        var UserRePwd = document.getElementById("UserRePw").value;


        if (UserEmail === null || UserEmail === '') {
            alert("이메일를 입력하세요");
            return false;
        }
        if (UserPwd === null || UserPwd === '') {
            alert("비밀번호를 입력하세요");
            return false;
        }
        if (UserRePwd === null || UserRePwd === '') {
            alert("2차비밀번호를 입력하세요");
            return false;
        }

        if (!(UserPwd === UserRePwd)) {
            alert("비밀번호가 같지 않습니다");

            return false;
        } else {
            return;
        }
    }