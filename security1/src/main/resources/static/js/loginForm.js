$(document).ready(function(){
    $(document).on('click', '#loginButton', function(){
        var loginEmail = document.getElementById("loginEmail").value;
        var loginPassword = document.getElementById("loginPassword").value;

        if (loginEmail === null || loginEmail === ''|| loginEmail === undefined) {
            $('#EmailValidateMsg').text("이메일 작성 해주세요");
            $('#EmailValidateMsg').show();
            return false;
        }
        if (loginPassword === null || loginPassword === ''|| loginPassword === undefined) {
            $('#passwordValidateMsg').text("비밀번호 작성 해주세요");
            $('#passwordValidateMsg').show();
            return false;
        } else {
            return;
        }
    });

    // 이메일 이벤트 처리
	$(document).on('keydown', '#loginEmail', function() {
        var userEmail = $('#loginEmail').val();

        if (validateEmail(userEmail) === true) {
             $('#EmailValidateMsg').hide();
        } else if (validateEmail(userEmail) === false) {
             $('#EmailValidateMsg').text("잘못된 이메일 형식입니다.");
             $('#EmailValidateMsg').show();
        }
    });

    // 비밀번호 이벤트 처리
  	$(document).on('keydown', '#loginPassword', function() {
    	var userPassword = $('#loginPassword').val();

    	if (validatePassword(userPassword) === true) {
            $('#passwordValidateMsg').hide();
        } else if (validatePassword(userPassword) === false) {
            $('#passwordValidateMsg').text("잘못된 비밀번호 형식입니다.");
            $('#passwordValidateMsg').show();
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

