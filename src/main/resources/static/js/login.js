/**
 *
 * 로그인 폼 처리
 */
function submitLoginForm(form) {
    const inputId = form.nickname;
    const inputPassword = form.password;

    inputId.value = inputId.value.trim();
    inputPassword.value = inputPassword.value.trim();

    const nickname = inputId.value;
    const password = inputPassword.value;

    if (nickname.length == 0) {
    alert('아이디를 입력해주세요.');
    inputId.focus();

    return;
}

    if (password.length == 0) {
    alert('비밀번호를 입력해주세요.');
    inputPassword.focus();

    return;
}


    form.submit();
}

