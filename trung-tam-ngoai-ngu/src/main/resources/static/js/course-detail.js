/**
 * Chuyển đổi qua lại giữa các Tab nội dung
 * @param {Event} evt - Sự kiện click
 * @param {string} tabName - ID của tab cần hiển thị
 */
function openTab(evt, tabName) {
    // 1. Ẩn tất cả các nội dung tab
    const panes = document.getElementsByClassName("tab-pane");
    for (let p of panes) {
        p.classList.remove("active");
    }

    // 2. Bỏ trạng thái active của tất cả các nút tab
    const links = document.getElementsByClassName("tab-link");
    for (let l of links) {
        l.classList.remove("active");
    }

    // 3. Hiển thị tab hiện tại và thêm class active vào nút đã nhấn
    const targetTab = document.getElementById(tabName);
    if (targetTab) {
        targetTab.classList.add("active");
    }

    evt.currentTarget.classList.add("active");
}