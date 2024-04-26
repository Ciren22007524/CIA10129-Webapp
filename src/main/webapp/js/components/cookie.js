new Vue({
    el: 'body', // 指定Vue实例挂载的元素为<body>元素，即整个页面
    data: {
        cookieDivOr: true // 定义一个数据属性cookieDivOr，用于控制cookie提示框的显示与隐藏
    },
    methods: {
        getCookie: function(cname) { // 定义获取cookie的方法，传入cookie名称cname
            var name = cname + "="; // 构造cookie名称的格式
            var ca = document.cookie.split(';'); // 将cookie字符串按分号分割成数组
            for (var i = 0; i < ca.length; i++) { // 遍历cookie数组
                var c = ca[i].trim(); // 去除每个cookie字符串的首尾空格
                if (c.indexOf(name) == 0) { // 如果找到对应的cookie
                    return c.substring(name.length, c.length); // 返回cookie值
                }
            }
            return ""; // 如果未找到对应的cookie，则返回空字符串
        },
        setCookie: function(cname, cvalue, exdays) { // 定义设置cookie的方法，传入cookie名称cname、cookie值cvalue和过期天数exdays
            var d = new Date(); // 创建一个Date对象
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000)); // 设置cookie的过期时间
            var expires = "expires=" + d.toUTCString(); // 构造expires字符串
            document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/"; // 设置cookie
        },
        clearCookie: function() { // 定义清除cookie的方法
            this.setCookie("cookieConsent", "", -1); // 调用setCookie方法，设置cookieConsent的值为空字符串，并将过期天数设置为-1，即立即过期
        },
        checkCookie: function() { // 定义检查cookie的方法
            var user = this.getCookie("cookieConsent"); // 调用getCookie方法获取cookieConsent的值
            if (user) { // 如果获取到了cookie值
                this.cookieDivOr = false; // 将cookieDivOr设置为false，隐藏cookie提示框
            } else { // 如果未获取到cookie值
                this.cookieDivOr = true; // 将cookieDivOr设置为true，显示cookie提示框
            }
        },
        closeDiv: function() { // 定义关闭cookie提示框的方法
            this.setCookie("cookieConsent", '1', 7); // 调用setCookie方法，设置cookieConsent的值为1，并将过期天数设置为7天
            this.cookieDivOr = false; // 将cookieDivOr设置为false，隐藏cookie提示框
        }
    },
    created() { // 在Vue实例创建时执行
        this.checkCookie(); // 调用checkCookie方法检查cookie
    }
});


window.onload = function() {
    var cookieDivOr = true;

    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
    }

    function clearCookie() {
        setCookie("cookieConsent", "", -1); // 设置过期日期为过去清除 cookie
    }

    function checkCookie() {
        var user = getCookie("cookieConsent");
        if (user) {
            cookieDivOr = false;
        } else {
            cookieDivOr = true;
        }
    }

    function closeDiv() {
        setCookie("cookieConsent", '1', 7); // 设置 cookie 值为 1，并保留 7 天
        cookieDivOr = false;
    }

    // 页面加载时执行
    checkCookie();

    // 可以在这里执行其他初始化操作
};

// JavaScript版
// 创建一个名为 CookieManager 的对象，用于管理 cookie 相关操作
var CookieManager = {
    cookieDivOr: true, // 控制 cookie 提示框的显示与隐藏

    // 获取指定名称的 cookie 值
    getCookie: function(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i].trim();
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    },

    // 设置 cookie
    setCookie: function(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
    },

    // 清除指定名称的 cookie
    clearCookie: function(cname) {
        document.cookie = cname + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    },

    // 检查是否存在指定名称的 cookie
    checkCookie: function(cname) {
        var user = this.getCookie(cname);
        if (user) {
            this.cookieDivOr = false;
        } else {
            this.cookieDivOr = true;
        }
    },

    // 关闭 cookie 提示框并设置 cookie
    closeDiv: function(cname) {
        this.setCookie(cname, '1', 7); // 设置 cookie 值为 1，并保留 7 天
        this.cookieDivOr = false;
    }
};

// 页面加载完成后执行初始化操作
window.onload = function() {
    CookieManager.checkCookie("cookieConsent"); // 检查 cookie
};

// 獲取其他cookie
// 添加商品到浏览历史
function addToHistory(product) {
    let history = JSON.parse(getCookie('history')) || [];
    // 检查是否已经存在于历史记录中
    if (!history.some(item => item.id === product.id)) {
        history.push({ id: product.id, name: product.name });
        // 最多保存最近的5个浏览记录
        if (history.length > 5) {
            history.shift();
        }
        setCookie('history', JSON.stringify(history), 30); // 保存30天
    }
}

// 添加商品到购物车
function addToCart(product) {
    let cart = JSON.parse(getCookie('cart')) || [];
    cart.push({ id: product.id, name: product.name, price: product.price });
    setCookie('cart', JSON.stringify(cart), 1); // 保存1天
}

// 保存用户偏好设置
function savePreferences(preferences) {
    setCookie('preferences', JSON.stringify(preferences), 365); // 保存1年
}

// 获取指定名称的 Cookie 值
function getCookie(name) {
    let cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        let [cookieName, cookieValue] = cookie.trim().split('=');
        if (cookieName === name) {
            return decodeURIComponent(cookieValue);
        }
    }
    return null;
}

// 设置 Cookie
function setCookie(name, value, days) {
    let expires = '';
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = '; expires=' + date.toUTCString();
    }
    document.cookie = name + '=' + encodeURIComponent(value) + expires + '; path=/';
}
