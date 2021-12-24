$(document).ready(function(){
    $(document).on('click', '#joinButton', function(){
        var userEmail = document.getElementById("userEmail").value;
        var userNickName = document.getElementById("userNickName").value;
        var userPassword = document.getElementById("userPassword").value;
        var userRePassword = document.getElementById("userRePassword").value;

        if (userEmail === null || userEmail === '' || userEmail === undefined) {
            $('#EmailValidateMsg').text("이메일 작성 해주세요");
            $('#EmailValidateMsg').show();
            $('.focus-email').focus();
            return false;
        }

        if (userNickName === null || userNickName === '' || userNickName === undefined) {
            $('#nicknameValidateMsg').text("닉네임 작성 해주세요");
            $('#nicknameValidateMsg').show();
            $('.focus-nickname').focus();
            return false;
        }

        if (userPassword === null || userPassword === '' || userPassword === undefined) {
            $('#passwordValidateMsg').text("비밀번호 작성 해주세요");
            $('#passwordValidateMsg').show();
            $('.focus-password').focus();
            return false;
        }
        if (userRePassword === null || userRePassword === '' || userRePassword === undefined) {
            $('#rePasswordValidateMsg').text("2차 비밀번호 작성 해주세요");
            $('#rePasswordValidateMsg').show();
            $('.focus-rePassword').focus();
            return false;
        }

        if (!(userPassword === userRePassword)) {
            alert("비밀번호가 같지 않습니다");

            return false;
        } else {
            return;
        }
    });

    $(document).on('click','#checkEmail',function(){
//       console.log(1);
    	var email = $('#userEmail').val()
//    	console.log(email);
        $.ajax({
            url:'/existsEmail/{email}',
            type:'GET',
            data: {'email' : $('userEmail').val()},
            dataType : "json",
            success : function(data) {
                if(data == true){
                    alert("중복된 이메일입니다.");
                } else {
                    return;
                }
            },
            error : function(data) {
                console.log(data);
                alert("잠시후 다시 시도해주세요.");
            }
        });
    });
    // 이메일 이벤트 처리
	$(document).on('keydown', '#userEmail', function() {
	    var userEmail = $('#userEmail').val();
        console.log(userEmail);

	    if (validateEmail(userEmail) === true) {
            $('#EmailValidateMsg').hide();
	    } else if (validateEmail(userEmail) === false) {
	        $('#EmailValidateMsg').text("잘못된 이메일 형식입니다.");
            $('#EmailValidateMsg').show();
	    }
  	});
  	// 닉네임 이벤트 처리
  	$(document).on('keydown', '#userNickName', function() {
    	var nickname = $('#userNickName').val();
    	if (validateNickname(nickname) === true) {
            $('#nicknameValidateMsg').hide();
    	} else if (validateNickname(nickname) === false) {
    	    $('#nicknameValidateMsg').text("잘못된 닉네임 형식입니다.");
    	    $('#nicknameValidateMsg').show();
    	}
    });
    // 비밀번호 이벤트 처리
    $(document).on('keydown', '#userPassword', function() {
	    var password = $('#userPassword').val();
	    if (validatePassword(password) === true) {
            $('#passwordValidateMsg').hide();
	    } else if (validatePassword(password) === false) {
	        $('#passwordValidateMsg').text("잘못된 비밀번호 형식입니다.");
	        $('#passwordValidateMsg').show();
	    }
  	});
  	// 2차 비밀번호 이벤트 처리
	$(document).on('keydown', '#userRePassword', function() {
	    var rePassword = $('#userRePassword').val();

	    if (validatePassword(password) === true) {
            $('#rePasswordValidateMsg').hide();
        } else if (validatePassword(password) === false) {
            $('#rePasswordValidateMsg').text("잘못된 비밀번호 형식입니다.");
            $('#rePasswordValidateMsg').show();
        }
    });

  	//이메일 유효성 검사
    function validateEmail(email) {
        var filter = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,3}$/
        if (filter.test(email)) {
            return true;
        } else {
            return false;
        }
    }

  	//닉네임 유효성 검사
    function validateNickname(nickname) {
        var filter = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$/
        if (filter.test(nickname)) {
            return true;
        } else {
            return false;
        }
    }

    //비밀번호 유효성 검사
    function validatePassword(password){
        var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
        if (pwd.test(password)) {
            return true;
        } else {
            return false;
        }
    }

    //2차 비밀번호 유효성 검사
    function validateRePassword(rePassword){
        var pwd = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
        if (pwd.test(rePassword)) {
            return true;
        } else {
            return false;
        }
    }
    //focus 이벤트
    $('.focused-email').focus();

    //체크박스 동의 이벤트
    $("#boardAgreementCheck").click(function(){
        var checkbox = $("input[name='checkbox']:checked");
        if($("#boardAgreementCheck").prop("checked")){
            $('#agreementCheck').hide();
            $("boardAgreementCheck").val(1);
        } else  {
            $('#agreementCheck').show();
            $("boardAgreementCheck").val(0);
        }
    });
});






