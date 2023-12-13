function submitWriteForm(form) {
    const inputTitle = form.title;
    const inputContent = form.content;

    inputTitle.value = inputTitle.value.trim();
    inputContent.value = inputContent.value.trim();

    const title = inputTitle.value;
    const content = inputContent.value;

    if (title.length == 0) {
        toastWarning('제목을 입력해주세요.');
        inputTitle.focus();

        return;
    }

    if (content.length == 0) {
        toastWarning('내용을 입력해주세요.');
        inputContent.focus();

        return;
    }


    form.submit();
}