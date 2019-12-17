import axios from 'axios'

export default new Request();

class Request{

    _router = "http://localhost:9100";
    _actionSecurity = this._router + "/actionSecurity";
    _authSecurity   = this._router + "/auth";

    actionSecurity(fn, errorfn = () => {}, finallyfn = () => {}) {
        axios.get(this._actionSecurity + "/checkAction").then(r => {
            fn(r);
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }

    authLogin(identifier, credential, fn, errorfn = () => {}, finallyfn = () => {}) {

        let f = new FormData();
        f.append("identifier", identifier);
        f.append("credential", credential);

        axios.post(this._authSecurity + "/login", f).then(r => {
            fn(r);
        }).catch(error => {
            errorfn(error)
        }).finally(() => {
            finallyfn()
        })
    }


}