$(document).ready(function(){
    $(document).on('click', '#joinButton', function(){
        var userEmail = document.getElementById("userEmail").value;
        var userNickName = document.getElementById("userNickName").value;
        var userPassword = document.getElementById("userPassword").value;
        var userRePassword = document.getElementById("userRePassword").value;


        if (userEmail === null || userEmail === '') {
            alert("이메일를 입력하세요");
            $('.focus-email').focus();
            return false;
        }

        if (userNickName === null || userNickName === '') {
            alert("닉네임을 입력하세요");
            $('.focus-nickname').focus();
            return false;
        }

        if (userPassword === null || userPassword === '') {
            alert("비밀번호를 입력하세요");
            $('.focus-password').focus();
            return false;
        }
        if (userRePassword === null || userRePassword === '') {
            alert("2차비밀번호를 입력하세요");
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
	    var email = $('#userEmail').val();

	    if (validateEmail(email)) {
            $('#email').hide();
            return false;
	    } else {
	        $('#email').show();
	    }
  	});
  	// 닉네임 이벤트 처리
  	$(document).on('keydown', '#userNickName', function() {
    	var nickname = $('#userNickName').val();
    	if (validateNickname(nickname)) {
            $('#nickname').hide();
            return false;
    	} else {
    	    $('#nickname').show();
    	}
    });
    // 비밀번호 이벤트 처리
    $(document).on('keydown', '#userPassword', function() {
	    var password = $('#userPassword').val();

	    if (validatePassword(password)) {
            $('#password').hide();
            return false;
	    } else {
	        $('#password').show();
	    }
  	});
  	// 2차 비밀번호 이벤트 처리
	$(document).on('keydown', '#userRePassword', function() {
	    var rePassword = $('#userRePassword').val();

	    if (validateRePassword(rePassword)) {
            $('#rePassword').hide();
            return false;
	    } else {
	        $('#rePassword').show();
	    }
  	});

  	//이메일 유효성 검사
    function validateEmail(email) {
        var filter = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,4}$/
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
});


$(document).ready(function() {
  $('.focused-email').focus();
});