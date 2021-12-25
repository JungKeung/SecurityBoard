/*
 * 이메일 형식 정규식
 */
function validateEmail(userEmail) {
    var filter = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,4}$/
    if (filter.test(userEmail)) {
        return true;
    } else {
        return false;
    }
}

/*
 * 패스워드 형식 정규식
 * 대소문자, 특수문자, 숫자 포함, 6~15글자 입력제한
 */
function validatePassword(userPassword){
    var filter = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,15}$/
    if (filter.test(userPassword)) {
        return true;
    } else {
        return false;
    }
}

function aaa() {
    console.log(1);
}
