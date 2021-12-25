$(document).ready(function(){
    // 로그인 이벤트 발생
    $(document).on('click', '#btnSubmit', function(){

        var userEmail = document.getElementById("userEmail").value;
        var userPassword = document.getElementById("userPassword").value;

        // 이메일 빈값 체크
        if (userEmail === null || userEmail === ''|| userEmail === undefined) {
            $('#EmailValidateMsg').text("이메일 작성 해주세요");
            $('#EmailValidateMsg').show();
            return false;
        }

        // 패스워드 빈값 체크
        if (userPassword === null || userPassword === ''|| userPassword === undefined) {
            $('#passwordValidateMsg').text("비밀번호 작성 해주세요");
            $('#passwordValidateMsg').show();
            return false;
        }

        // 이메일 형식 체크
        if (validateEmail(userEmail) === false) {
            $('#EmailValidateMsg').text("잘못된 이메일 형식입니다.");
            $('#EmailValidateMsg').show();
            return false;
        }
    });

    // 이메일 입력 후 형식이 맞다면 에러메세지 미노출
	$(document).on('keydown', '#userEmail', function() {
        var userEmail = $('#userEmail').val();

        // 이메일 형식 체크
        if (validateEmail(userEmail) === true) {
             $('#EmailValidateMsg').hide();
        } else if (validateEmail(userEmail) === false) {
             $('#EmailValidateMsg').text("잘못된 이메일 형식입니다.");
             $('#EmailValidateMsg').show();
        }
    });

    // 패스워드 입력 후 형식이 맞다면 에러메세지 미노출
  	$(document).on('keydown', '#userPassword', function() {
    	var userPassword = $('#userPassword').val();

        // 패스워드 형식 체크
    	if (validatePassword(userPassword) === true) {
            $('#passwordValidateMsg').hide();
        } else if (validatePassword(userPassword) === false) {
            $('#passwordValidateMsg').text("잘못된 비밀번호 형식입니다.");
            $('#passwordValidateMsg').show();
        }
    });
});

