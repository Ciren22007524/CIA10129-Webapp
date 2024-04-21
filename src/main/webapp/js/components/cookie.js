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