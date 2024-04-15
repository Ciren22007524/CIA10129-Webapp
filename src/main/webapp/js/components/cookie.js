getCookie: function (cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        console.log(c, 'c');
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) != -1) {
            return c.substring(name.length, c,length)
        }
    }
    return "";
}

setCookie: function (cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
    var expires = "expires=" + d.toUTCString();
    console.info(cname + "=" + cvalue + "; " + expires);
    document.cookie = cname + "=" + cvalue + "; " + expires;
    console.log(document.cookie);
}

clearCookie: function () {
    this.setCookie("傳給cookie的值可以自定義", "",-1000) // 設置天數
}

checkCookie: function () {
    var user = this.getCookie("傳給cookie的值可以自定義")
    if (user != "") {
        this.cookieDivOr = false;
    } else {
        this.cookieDivOr = true;
    }
}

closeDiv() {
    this.setCookie("傳給cookie的值可以自定義", '1', 1000)
    this.cookieDivOr = false;
}

export default {
    data() {
        return {
            cookieDivOr: true
        }
    }
}

create() {
    this.cookie();
}