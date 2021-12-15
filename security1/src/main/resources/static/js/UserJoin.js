document.getElementById("JoinBtn").onclick = function () {
        var UserEmail = document.getElementById("UserEmail").value;
        var UserNickName = document.getElementById("UserNickName").value;
        var UserPwd = document.getElementById("UserPw").value;
        var UserRePwd = document.getElementById("UserRePw").value;


        if (UserEmail === null || UserEmail === '') {
            alert("이메일를 입력하세요");

            return false;
        }

        if (UserNickName === null || UserNickName === '') {
            alert("닉네임을 입력하세요");

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

function chkPw(){
    var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,15}$/;

    var pw = $("#UserPw").val();

    if(false === reg.test(pw)) {
        alert('비밀번호는 6자 이상이어야 하며, 숫자/소문자/특수문자를 모두 포함해야 합니다.');
    } else {
        console.log("통과");

    }
}
