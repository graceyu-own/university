import Vue from 'vue'

import Router from 'vue-router'
import Index from "@/components/Index";
import Login from "@/components/auth/Login";
import Register from "@/components/auth/Register";
import Center from "@/components/center/Center";
import ResetPassword from "@/components/auth/ResetPassword";
import SetPassword from "@/components/auth/SetPassword";
import Info from "@/components/info/Info";

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
            path: '/info/:msg',
            name: 'Info',
            component: Info,
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
            path: '/auth/reset-password',
            name: 'resetPassword',
            component: ResetPassword,
        },
        {
            path: '/auth/set-password',
            name: 'setPassword',
            component: SetPassword,
        },
        {
            path: '/center',
            name: 'center',
            component: Center,
            meta: {
                keepAlive: true
            }
        }
    ]
})