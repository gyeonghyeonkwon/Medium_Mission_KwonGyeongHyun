/**
 *
 * 회원 가입 폼 처리
 */
function submitJoinForm(form) {
    const inputs = {
        'username': '이름',
        'nickname': '닉네임',
        'password': '비밀번호',
        'passwordConfirm': '비밀번호 확인'
    };
    /**
     * 회원 가입 입력 공백 시 알림 표시
     */
    for (const inputName in inputs) {
        const inputValue = form[inputName].value.trim();

        if (inputValue.length === 0) {
            alert(`${inputs[inputName]}을 (를) 입력해주세요.`);
            form[inputName].focus();
            return;
        }
        /**
         * 입력된 패스 워드 가 일치 하지 않을 시 알림을 표시
         */
        const password = form['password'].value.trim();
        const passwordConfirm = form['passwordConfirm'].value.trim();

        if (password !== passwordConfirm) {
            alert('입력하신 비밀번호가 일치하지 않습니다.');
            form['password'].focus();
            return;
        }
    }

    form.submit();
}