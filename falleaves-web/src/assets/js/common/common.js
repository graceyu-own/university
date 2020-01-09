import Vue from 'vue'

/**
 * 这是一个window的本地储存类, 用于便捷式操作localStorage
 * @Author Graceyu
 */
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
        this.start = 0;
        this.stop  = 0;
    }

    static init() {
        return new Timings();
    }

    startTimings() {
        this.start = Date.now()
    }

    stopTimings() {
        this.stop = Date.now();
    }

    calc() {
        return {
            milliseconds    : this.stop - this.start,
            seconds         : (this.stop - this.start) / 1000,
            minutes         : (this.stop - this.start) / 1000 / 60
        }
    }

}

class Timeout {

    constructor(timeout = 100000, callback = () => {}) {
        this.timeout = timeout;
        this.callback = callback
    }

    execute() {

    }
}

function registry() {
    Vue.prototype.$common = {
        attr    : new Attr(),
        timings : Timings,
        timeout : Timeout
    }
}

export default registry;