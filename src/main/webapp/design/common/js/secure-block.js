// secure-block.js

(function () {
    // ✅ 1. 우클릭(마우스 오른쪽 버튼) 차단
    document.addEventListener('contextmenu', function (e) {
        e.preventDefault();
    });

    // ✅ 2. F12, Ctrl+Shift+I, Ctrl+Shift+J, Ctrl+U 등 단축키 차단
    document.addEventListener('keydown', function (e) {
        if (
            e.key === "F12" || e.keyCode === 123 || // F12
            (e.ctrlKey && e.shiftKey && (e.key === 'I' || e.key === 'J')) || // Ctrl+Shift+I/J
            (e.ctrlKey && e.key === 'U') // Ctrl+U (소스 보기)
        ) {
            e.preventDefault();
        }
    });

    // ✅ 3. 개발자 도구 감지 (브라우저 창 크기 이상 감지)
    const threshold = 160;
    setInterval(function () {
        if (
            window.outerWidth - window.innerWidth > threshold ||
            window.outerHeight - window.innerHeight > threshold
        ) {
            document.body.innerHTML = "<h1 style='text-align:center; padding-top:50px;'>개발자 도구 사용이 차단되었습니다.</h1>";
        }
    }, 1000);
})();
