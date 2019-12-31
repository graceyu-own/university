

class Attr{

    set (key, value, expire) {
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
            if(-1 === time || time >= new Date().getTime()) {
                return true;
            }
        }
        return false;
    }

}

export default new Attr()