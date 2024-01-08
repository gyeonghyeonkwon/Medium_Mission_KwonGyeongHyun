// 페이지 로드 시 세션 스토리지에서 검색 조건 읽어오기
window.onload = function() {
    const storedSearchTarget = sessionStorage.getItem('searchType');
    const storedKw = sessionStorage.getItem('kw');

    if (storedSearchTarget) {
        document.getElementById("searchType").value = storedSearchTarget;
    }

    if (storedKw) {
        document.getElementById("search_kw").value = storedKw;
    }
}

// 페이지 이동 시 검색 조건을 세션 스토리지에 저장하고 폼 제출
const page_elements = document.getElementsByClassName("page-link");
Array.from(page_elements).forEach(function(element) {
    element.addEventListener('click', function() {
        document.getElementById('page').value = this.dataset.page;
        saveSearchCondition();
        document.getElementById('searchForm').submit();
    });
});

// 검색 버튼 클릭 시 검색 조건을 세션 스토리지에 저장하고 폼 제출
const btn_search = document.getElementById("btn_search");
btn_search.addEventListener('click', function() {
    document.getElementById('kw').value = document.getElementById('search_kw').value;
    saveSearchCondition();
    document.getElementById('page').value = 1;  // 검색 버튼을 클릭할 경우 1페이지부터 조회
    document.getElementById('searchForm').submit();
});

// 검색 조건을 세션 스토리지에 저장하는 함수
function saveSearchCondition() {
    const selectedTarget = document.getElementById("searchType").value;
    const searchInput = document.getElementById("search_kw").value;

    // 검색 조건 세션 스토리지에 저장
    sessionStorage.setItem('searchType', selectedTarget);
    sessionStorage.setItem('kw', searchInput);
}

// 검색 조건을 폼에 설정하고 제출하는 함수
function submitForm() {
    const selectedValue = document.getElementById("searchType").value;
    document.getElementById("searchTypeHidden").value = selectedValue;
    saveSearchCondition();
    document.getElementById("searchForm").submit();
}