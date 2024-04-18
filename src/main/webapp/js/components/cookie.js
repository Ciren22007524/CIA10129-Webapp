new Vue({
    el: 'body',
    data: {
        cookieDivOr: true
    },
    methods: {
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
        setCookie: function(cname, cvalue, exdays) {
            var d = new Date();
            d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
            var expires = "expires=" + d.toUTCString();
            document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
        },
        clearCookie: function() {
            this.setCookie("cookieConsent", "", -1); // 設置過期日期為過去清除 cookie
        },
        checkCookie: function() {
            var user = this.getCookie("cookieConsent");
            if (user) {
                this.cookieDivOr = false;
            } else {
                this.cookieDivOr = true;
            }
        },
        closeDiv: function() {
            this.setCookie("cookieConsent", '1', 365); // 設置 cookie 值為 1，並保留 365 天
            this.cookieDivOr = false;
        }
    },
    created() {
        this.checkCookie();
    }
});