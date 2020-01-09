import Vue from 'vue'
import axios from 'axios'

class Request {

    constructor() {
        axios.defaults.timeout = 10000;
    }
}

class AuthRequest extends Request{

    constructor() {
        super();
    }

    /*_router = "http://localhost:8200";
    _authService = this._router + "/auth";*/
    _authService = "http://127.0.0.1:9100";

    /*AuthCheckLogin (fn, errorfn = () => {}, finallyfn = () => {}) {
        axios.get(this._authService + "/checkLogin").then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn();
        })
    }*/

    behavior (fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "get",
            url     : this._authService + "/check-behavior",
            withCredentials: true
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    login (identifier, credential, behaviorValid, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "post",
            url     : this._authService + "/login",
            withCredentials: true,
            data: {
                identifier      : identifier,
                credential      : credential,
                behaviorValid   : behaviorValid
            }
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    register (nickname, email, emailValid, password, password2, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "post",
            url     : this._authService + "/register",
            withCredentials: true,
            data: {
                nickname    : nickname,
                email       : email,
                emailValid  : emailValid,
                password    : password,
                password2   : password2
            }
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    sendRegisterMail (email, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "post",
            url     : this._authService + "/send-register-mail",
            withCredentials: true,
            data: {
                email: email
            }
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    resetPassword (email, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "post",
            url     : this._authService + "/reset-password",
            withCredentials: true,
            data: {
                email: email
            }
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    setPassword (data, password, password2, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "post",
            url     : this._authService + "/set-password",
            withCredentials: true,
            data: {
                data        : data,
                password    : password,
                password2   : password2
            }
        }).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }
}

class RegionRequest extends Request{

    constructor() {
        super();
    }

    QueryAll(fn, errorfn = () => {}, finallyfn = () => {}) {

        axios({
            method: "get",
            url : "https://api02.aliyun.venuscn.com/area/all?level=0&page=1&size=50",
            headers: {
                Authorization: "APPCODE 30e0f184da5c4ed3ba0afb3fbaf24364"
            }
        }).then(r => {
            fn(r)
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn()
        })
    }

    QueryById(id, fn, errorfn = () => {}, finallyfn = () => {}) {
        axios({
            method: "get",
            url : "https://api02.aliyun.venuscn.com/area/query?parent_id=" + id,
            headers: {
                Authorization: "APPCODE 30e0f184da5c4ed3ba0afb3fbaf24364"
            }
        }).then(r => {
            fn(r)
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn()
        })
    }


}

class ResponseData {

    constructor(codeType, codeAppend, data) {
        this.codeType = codeType;
        this.codeAppend = codeAppend;
        this.data = data;
    }

}

function registry() {
    Vue.prototype.$request = {
        auth    : new AuthRequest(),
        region  : new RegionRequest()
    }
}

export default registry