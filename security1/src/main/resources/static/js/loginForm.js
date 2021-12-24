$(document).ready(function(){
    $(document).on('click', '#loginButton', function(){
        var loginEmail = document.getElementById("loginEmail").value;
        var loginPassword = document.getElementById("loginPassword").value;

        if (loginEmail === null || loginEmail === '' || loginPassword === undefined) {
            alert("아이디를 입력하세요");
            return false;
        }
        if (loginPassword === null || loginPassword === '' || loginPassword === undefined) {
            alert("비밀번호를 입력하세요");
            return false;
        } else {
            return;
        }
    });

    // 이메일 이벤트 처리
	$(document).on('keydown', '#loginEmail', function() {
        var userEmail = $('#loginEmail').val();

        if (validateEmail(userEmail)) {
            $('#email').hide();
            return false;
        } else{
            $('#email').show();
        }
    });

    // 비밀번호 이벤트 처리
  	$(document).on('keydown', '#loginPassword', function() {
    	var userPassword = $('#loginPassword').val();

    	if (validatePassword(userPassword)) {
            $('#password').hide();
            return false;
    	} else {
    	    $('#password').show();
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
    function validatePassword(userPassword){
        var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
        if (pwd.test(userPassword)) {
            return true;
        } else {
            return false;
        }
    }
});

