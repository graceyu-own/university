import axios from 'axios'
import da from "element-ui/src/locale/lang/da";

class Request{

    constructor() {
        axios.defaults.withCredentials = true;
        axios.defaults.timeout = 10000;
    }

    /*_router = "http://localhost:8200";
    _authService = this._router + "/auth";*/
    _authService = "http://127.0.0.1:9100";

    AuthBehavior (fn, errorfn = () => {}, finallyfn = () => {}) {
        axios.get(this._authService + "/checkBehavior").then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    AuthLogin (identifier, credential, behaviorValid, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();
        f.append("identifier", identifier);
        f.append("credential", credential);
        f.append("behaviorValid", behaviorValid);

        axios.post(this._authService + "/login", f).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    AuthRegister (nickname, email, emailValid, password, password2, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();
        f.append("nickname", nickname);
        f.append("email", email);
        f.append("emailValid", emailValid);
        f.append("password", password);
        f.append("password2", password2);

        axios.post(this._authService + "/register", f).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn()
        })
    }

    AuthResetPassword (email, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();

        f.append("email", email);

        axios.post(this._authService + "/resetPassword", f).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn();
        })

    }

    SendRegisterMail (email, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();

        f.append("email", email);

        axios.post(this._authService + "/sendRegisterMail", f).then(r => {
            fn(new ResponseData(r.data.codeType, r.data.codeAppend, r.data.data));
        }).catch(error => {
            errorfn(error);
        }).finally(() => {
            finallyfn()
        })
    }

    AuthSetPassword (data, password, password2, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();
        f.append("data", data);
        f.append("password", password);
        f.append("password2", password2);

        axios.post(this._authService + "/setPassword", f).then(r => {
            fn(new ResponseData(r.data, r.codeType, r.data.codeAppend, r.data.data));
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

export default new Request()