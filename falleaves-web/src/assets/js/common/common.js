import Vue from 'vue'

//////////////////////////////////////////////////////////////////
//
//  Common-Attr 本地储存类, 用于便捷式操作localStorage
//
//  @Author Grace.Yu
//
//////////////////////////////////////////////////////////////////
class Attr {

    set (key, value, expire = -1) {
        window.localStorage[key] = value;
        if(null == expire || expire === -1) {
            window.localStorage[key + ":expire"] = -1;
        } else {
            window.localStorage[key + ":expire"] = new Date().getTime() + expire;
        }

    }

    delete (key) {
        window.localStorage.removeItem(key);
        window.localStorage.removeItem(key + ":expire");
    }

    get (key) {
        if(this.has(key)) {
            return window.localStorage[key];
        }
        return null;
    }

    has (key) {
        if(window.localStorage.hasOwnProperty(key + ":expire")) {
            let time = window.localStorage[key + ":expire"];
            if(-1 == time || time >= new Date().getTime()) {
                return true;
            }
        }
        return false;
    }
}

//////////////////////////////////////////////////////////////////
//
//
//
//
//////////////////////////////////////////////////////////////////
class Timings {

    constructor() {
        this._start = 0;
        this._stop  = 0;
    }

    static Init() {
        return new Timings();
    }

    startTimings() {
        this._start = Date.now()
    }

    stopTimings() {
        this._stop = Date.now();
    }

    calc() {
        return {
            milliseconds    : this._stop - this._start,
            seconds         : (this._stop - this._start) / 1000,
            minutes         : (this._stop - this._start) / 1000 / 60
        }
    }

}

//////////////////////////////////////////////////////////////////
//
//
//
//
//////////////////////////////////////////////////////////////////
class Timeout {

    constructor(timeout = 100000, callback = () => {}) {
        this.timeout = timeout;
        this.callback = callback
    }

    execute() {

    }
}

//////////////////////////////////////////////////////////////////
//
//  Common-Utils 工具类, 一些常用的方法
//
//  @Author Grace.Yu
//
//////////////////////////////////////////////////////////////////
class Watch {

    /**
     * 监听屏幕大小发生变化
     *
     * @param callback(width, height)   回调函数
     * @param period                    监听频率(毫秒为单位, default = 250)
     */
    watchWindow(callback, period = 250) {
        this.watchElement(window, callback, period);
    }

    /**
     * 监听元素大小发生变化
     *
     * @param element                   需要监听的元素
     * @param callback(width, height)   回调函数
     * @param period                    监听频率(毫秒为单位)
     */
    watchElement(element, callback, period) {

        let width = document.body.offsetWidth;
        let height = document.body.offsetHeight;
        let timer = null;

        callback(width, height);

        window.addEventListener("resize", () => {
            if(timer) clearTimeout(timer);

            timer = setTimeout(() => {
                width = document.body.offsetWidth;
                height = document.body.offsetHeight;
                callback(width, height);
            }, period);
        })
    }
}


function registry() {
    Vue.prototype.$common = {

        // use singleton class
        attr        : new Attr(),
        watch       : new Watch(),

        // use static method Init()
        timings     : Timings,

        // default
        timeout     : Timeout,

    }
}

export default registry;