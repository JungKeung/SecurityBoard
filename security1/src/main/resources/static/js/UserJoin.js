document.getElementById("JoinBtn").onclick = function () {
        var UserEmail = document.getElementById("UserEmail").value;
        var UserNickName = document.getElementById("UserNickName").value;
        var UserPwd = document.getElementById("UserPw").value;
        var UserRePwd = document.getElementById("UserRePw").value;


        if (UserEmail === null || UserEmail === '') {
            alert("이메일를 입력하세요");
            $('.focus-email').focus();
            return false;
        }

        if (UserNickName === null || UserNickName === '') {
            alert("닉네임을 입력하세요");
            $('.focus-nickname').focus();
            return false;
        }

        if (UserPwd === null || UserPwd === '') {
            alert("비밀번호를 입력하세요");
            $('.focus-password').focus();
            return false;
        }
        if (UserRePwd === null || UserRePwd === '') {
            alert("2차비밀번호를 입력하세요");
            $('.focus-repassword').focus();
            return false;
        }

        if (!(UserPwd === UserRePwd)) {
            alert("비밀번호가 같지 않습니다");

            return false;
        } else {
            return;
        }
    }

$(document).ready(function(){
    // 이메일 이벤트 처리
	$(document).on('keydown', '#UserEmail', function() {
	    var email = $('#UserEmail').val();

	    if (validateEmail(email)) {
            $('#Email_div').hide();
            return false;
	    } else{
	        $('#Email_div').show();
	    }
  	});
  	// 닉네임 이벤트 처리
  	$(document).on('keydown', '#UserNickName', function() {
    	var nickname = $('#UserNickName').val();
    	if (validateNickname(nickname)) {
            $('#Nickname_div').hide();
            return false;
    	} else{
    	    $('#Nickname_div').show();
    	}
    });
    // 비밀번호 이벤트 처리
    $(document).on('keydown', '#UserPw', function() {
	    var pass = $('#UserPw').val();

	    if (validatePassword(pass)) {
            $('#Password_div').hide();
            return false;
	    } else {
	        $('#Password_div').show();
	    }
  	});
  	// 2차 비밀번호 이벤트 처리
	$(document).on('keydown', '#UserRePw', function() {
	    var Repass = $('#UserRePw').val();

	    if (validateRePassword(Repass)) {
            $('#RePassword_div').hide();
            return false;
	    } else {
	        $('#RePassword_div').show();
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

  	//닉네임 유효성 검사
    function validateNickname(sNickname) {
        var filter = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/
        if (filter.test(sNickname)) {
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

    //2차 비밀번호 유효성 검사
    function validateRePassword(sRePassword){
        var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
        if (pwd.test(sRePassword)) {
            return true;
        } else {
            return false;
        }
    }
});


$(document).ready(function() {
  $('.focused-email').focus();
});



