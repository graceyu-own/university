import Vue from 'vue'
import Router from 'vue-router'
import Index from "@/components/Index";
import Login from "@/components/auth/Login";
import Register from "@/components/auth/Register";
import Center from "@/components/Center";

Vue.use(Router);

export default new Router({
    mode: 'hash',
    routes: [
        {
            path: '/',
            name: 'index',
            component: Index,
        },
        {
            path: '/auth/login',
            name: 'login',
            component: Login,
        },
        {
            path: '/auth/register',
            name: 'register',
            component: Register,
        },
        {
            path: '/center',
            name: 'center',
            component: Center,
        }
    ]
})