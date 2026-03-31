/**
 * Xử lý điều hướng và hiệu ứng click
 * @param {string} featureName - Tên tính năng được click
 */
function handleNavigation(featureName) {
    console.log(`Đang điều hướng tới: ${featureName}`);

    // Tạo hiệu ứng feedback khi click (nếu muốn)
    // Ở đây có thể điều hướng trang thực tế:
    // window.location.href = '/student/' + featureName.toLowerCase();

    // Ví dụ về thông báo đơn giản
    const message = `Bạn đã chọn chức năng: ${featureName}`;

    // Animation đơn giản trước khi chuyển trang
    const container = document.querySelector('.dashboard-container');
    container.style.opacity = '0.5';
    container.style.transition = '0.3s';

    setTimeout(() => {
        alert(message);
        container.style.opacity = '1';
    }, 200);
}

// Lắng nghe sự kiện load để có thể thêm các hiệu ứng khác nếu cần
document.addEventListener('DOMContentLoaded', () => {
    console.log("Student Dashboard Ready!");
});