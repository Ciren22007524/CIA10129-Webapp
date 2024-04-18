/* 浮動視窗功能 */
$(document).ready(function () {
    // 點擊圖片時顯示浮動層
    $('img').on('click', function () {
        // 設置放大圖片的 src
        $('#enlarged-image').attr('src', $(this).attr('src'));
        // 顯示浮動層
        $('#overlay').fadeIn();
    });

    // 點擊關閉按鈕隱藏浮動層
    $('#close-btn').on('click', function () {
        $('#overlay').fadeOut();
    });

    // 點擊浮動層外部區域隱藏浮動層
    $('#overlay').on('click', function (event) {
        if (event.target.id === 'overlay') {
            $('#overlay').fadeOut();
        }
    });

    // 綁定浮動層中的 img 滾輪事件
    $('#enlarged-image').on('wheel', function (event) {
        // 阻止默認滾輪行為
        event.preventDefault();

        // 獲取當前縮放級別
        let currentScale = $(this).data('scale') || 1;

        // 根據滾輪方向調整縮放級別
        if (event.originalEvent.deltaY < 0) {
            currentScale *= 1.1; // 放大
        } else {
            currentScale /= 1.1; // 縮小
        }

        // 限制縮放級別範圍
        currentScale = Math.min(Math.max(currentScale, 0.5), 5);

        // 應用新的縮放級別
        $(this).css('transform', 'scale(' + currentScale + ')');

        // 保存新的縮放級別
        $(this).data('scale', currentScale);
    });

    // 顯示浮動視窗時設置圖片和跑馬燈
    $('.image-thumbnail img').on('click', function () {
        // 設置放大圖片的 src
        const imgSrc = $(this).attr('src');
        $('#enlarged-image').attr('src', imgSrc);

        // 顯示浮動視窗
        $('#overlay').fadeIn();

        // 更新跑馬燈中選中的縮略圖
        const currentImage = $(this).index();
        updateCarousel(currentImage);
    });

    // 關閉浮動視窗
    $('#close-btn').on('click', function () {
        $('#overlay').fadeOut();
    });

    // 點擊浮動層外部區域隱藏浮動層
    $('#overlay').on('click', function (event) {
        if (event.target.id === 'overlay') {
            $('#overlay').fadeOut();
        }
    });

    // 綁定縮略圖點擊事件
    $('.carousel-thumbnail').on('click', function () {
        const index = $(this).index();
        updateOverlayImage(index);
    });

    // 左右箭頭點擊事件
    $('#prev-btn').on('click', function () {
        changeImage(-1);
    });

    $('#next-btn').on('click', function () {
        changeImage(1);
    });

    // 函數更新浮動視窗中的圖片
    function updateOverlayImage(index) {
        const newSrc = $('.carousel-thumbnail').eq(index).attr('src');
        $('#enlarged-image').attr('src', newSrc);
    }

    // 更新跑馬燈的選中狀態
    function updateCarousel(index) {
        $('.carousel-thumbnail').removeClass('active');
        $('.carousel-thumbnail').eq(index).addClass('active');
    }

    // 改變圖片（上一張或下一張）
    function changeImage(direction) {
        const currentIndex = $('.carousel-thumbnail.active').index();
        const newIndex = (currentIndex + direction + $('.carousel-thumbnail').length) % $('.carousel-thumbnail').length;

        // 更新浮動視窗中的圖片
        updateOverlayImage(newIndex);

        // 更新跑馬燈的選中狀態
        updateCarousel(newIndex);
    }
});