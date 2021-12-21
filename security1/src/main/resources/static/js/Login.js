document.getElementById("LoginBtn").onclick = function () {
        var LoginEmail = document.getElementById("LoginEmail").value;
        var UserPwd = document.getElementById("LoginPassword").value;


        if (LoginEmail === null || LoginEmail === '') {
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

$(document).ready(function(){
    // 이메일 이벤트 처리
	$(document).on('keydown', '#LoginEmail', function() {
	    var email = $('#LoginEmail').val();

	    if (validateEmail(email)) {
            $('#Email_div').hide();
            return false;
	    } else{
	        $('#Email_div').show();
	    }
  	});
    // 비밀번호 이벤트 처리
  	$(document).on('keydown', '#LoginPassword', function() {
    	var pass = $('#LoginPassword').val();

    	if (validatePassword(pass)) {
            $('#Password_div').hide();
            return false;
    	} else {
    	    $('#Password_div').show();
    	}
    });

//이메일 유효성 검사
function validateEmail(sEmail) {
    var filter = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,4}$/
    if (filter.test(sEmail)) {
        return true;
    } else {
        return false;
    }
}

//비밀번호 유효성 검사
function validatePassword(sPassword){
    var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
    if (pwd.test(sPassword)) {
        return true;
    } else {
        return false;
    }
}
});

