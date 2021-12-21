

$(document).ready(function(){
    $(document).on('click', '#LoginBtn', function(){
        var userEmail = document.getElementById("LoginEmail").value;
        var userPwd = document.getElementById("LoginPassword").value;

        if (userEmail === null || userEmail === '') {
            alert("아이디를 입력하세요");
            return false;
        }
        if (userPwd === null || userPwd === '') {
            alert("비밀번호를 입력하세요");
            return false;
        } else {
            return;
        }
    });

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
    	var userPwd = $('#LoginPassword').val();

    	if (validatePassword(userPwd)) {
            $('#Password_div').hide();
            return false;
    	} else {
    	    $('#Password_div').show();
    	}
    });

    //이메일 유효성 검사
    function validateEmail(userEmail) {
        var filter = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,4}$/
        if (filter.test(userEmail)) {
            return true;
        } else {
            return false;
        }
    }

    //비밀번호 유효성 검사
    function validatePassword(userPwd){
        var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
        if (pwd.test(userPwd)) {
            return true;
        } else {
            return false;
        }
    }
});

