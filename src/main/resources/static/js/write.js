    function submitEditForm(form) {
    form.title.value = form.title.value.trim();

    if (form.title.value.length == 0) {
    toastWarning('제목을 입력해주세요.');
    form.title.focus();
    return;
}

    const editor = $(form).find(".toast-ui-editor").data("data-toast-editor");

    const markdown = editor.getMarkdown().trim();

    form.content.value = markdown;

    if (form.content.value.length == 0) {
    toastWarning("내용을 입력해주세요");
    editor.focus();
    return;
}

    form.submit();
}
