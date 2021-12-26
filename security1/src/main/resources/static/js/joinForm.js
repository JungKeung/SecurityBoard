$(document).ready(function(){
    // 페이지 로드 후 바로 이메일 입력 가능하도록 설정
    $('#userEmail').focus();

    // 회원가입 처리
    $(document).on('click', '#btnSubmit', function(){

        var userEmail = $('#userEmail').val();
        var userNickName = $('#userNickName').val();
        var userPassword = $('#userPassword').val();
        var userRePassword = $('#userRePassword').val();
        var isAgreePrivacyTerms = $('#chkAgreePrivacyTerms').is(":checked");
        var isAgreeMarketingTerms = $('#chkAgreeMarketingTerms').is(":checked");

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

        if (userPassword !== userRePassword) {
            $('#rePasswordValidateMsg').text("비밀번호가 같지 않습니다");
            $('#rePasswordValidateMsg').show();
            return false;
        }

        if (isAgreePrivacyTerms === false) {
            $('#termsValidateMsg').text("서비스 이용약관 동의는 필수입니다.");
            $('#termsValidateMsg').show();
            return false;
        }

        var data = {
            'email' : userEmail,
            'password' : userPassword,
            'nickname' : userNickName,
            'isAgreePrivacyTerms' : isAgreePrivacyTerms,
            'isAgreeMarketingTerms' : isAgreeMarketingTerms
        }
        console.log('request');
        console.log(data);

        $.ajax({
            url:'/join',
            type:'POST',
            data: data,
            dataType : "json",
            success : function(result) {
                if (result.errorCode === 0) {
                    console.log(result.data);
                    //alert("회원가입이 완료되었습니다.");
                    //location.href = "/login";
                } else {
                    alert("회원가입이 실패하였습니다.");
                    console.log(result.errorCode);
                    console.log(result.errorMessage);
                }
            },
            error : function(err) {
                console.log(err);
                alert("잠시후 다시 시도해주세요.");
            }
        });
    });

    $(document).on('click','#checkEmail',function(){

    	var email = $('#userEmail').val();

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

    // 이메일 입력에 따른 유효성 체크
	$(document).on('keydown', '#userEmail', function() {
	    var userEmail = $('#userEmail').val();

	    if (validateEmail(userEmail) === true) {
            $('#EmailValidateMsg').hide();
	    } else if (validateEmail(userEmail) === false) {
	        $('#EmailValidateMsg').text("잘못된 이메일 형식입니다.");
            $('#EmailValidateMsg').show();
	    }
  	});

  	// 닉네임 입력에 따른 유효성 체크
  	$(document).on('keydown', '#userNickName', function() {
    	var nickname = $('#userNickName').val();

    	if (validateNickname(nickname) === true) {
            $('#nicknameValidateMsg').hide();
    	} else if (validateNickname(nickname) === false) {
    	    $('#nicknameValidateMsg').text("잘못된 닉네임 형식입니다.");
    	    $('#nicknameValidateMsg').show();
    	}
    });

    // 비밀번호 입력에 따른 유효성 체크
    $(document).on('keydown', '#userPassword', function() {
	    var password = $('#userPassword').val();

	    if (validatePassword(password) === true) {
            $('#passwordValidateMsg').hide();
	    } else if (validatePassword(password) === false) {
	        $('#passwordValidateMsg').text("잘못된 비밀번호 형식입니다.");
	        $('#passwordValidateMsg').show();
	    }
  	});

  	// 2차 비밀번호 입력에 따른 유효성 체크
	$(document).on('keydown', '#userRePassword', function() {
	    var rePassword = $('#userRePassword').val();
        var userRePassword = $('#userRePassword').val();

	    if (validatePassword(rePassword) === true) {
            $('#rePasswordValidateMsg').hide();
        } else if (validatePassword(rePassword) === false) {
            $('#rePasswordValidateMsg').text("잘못된 비밀번호 형식입니다.");
            $('#rePasswordValidateMsg').show();
        }
    });

    // 이용약관 전체선택 이벤트
    $(document).on('click', '#chkAgreeAllTerms', function() {
        var isAgreeAllTerms = $('#chkAgreeAllTerms').is(":checked");

        if (isAgreeAllTerms === true) {
            $('input:checkbox[class="checkbox"]').each(function() {
                this.checked = true;
            });
        } else {
            $('input:checkbox[class="checkbox"]').each(function() {
                this.checked = false;
            });
        }
    });

    // 서비스 이용약관 필수선택 이벤트
    $(document).on('click', '#chkAgreePrivacyTerms', function() {
        var isAgreePrivacyTerms= $('#chkAgreePrivacyTerms').is(":checked");

        if (isAgreePrivacyTerms === true) {
            $('#termsValidateMsg').hide();
        } else {
            $('#termsValidateMsg').text("서비스 이용약관 동의는 필수입니다.");
            $('#termsValidateMsg').show();
        }
    });
});






