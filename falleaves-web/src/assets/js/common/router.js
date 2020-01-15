import Vue from 'vue'

import Router from 'vue-router'
import Index from "@/components/Index";
import Login from "@/components/auth/Login";
import Register from "@/components/auth/Register";
import Center from "@/components/center/Center";
import ResetPassword from "@/components/auth/ResetPassword";
import SetPassword from "@/components/auth/SetPassword";
import Info from "@/components/info/Info";
import Test from "@/components/Test";
import CenterSearchUniversity from "@/components/center/search/university/CenterSearchUniversity";
import CenterSearchMajor from "@/components/center/search/major/CenterSearchMajor";
import CenterSearchUniversityInfo from "@/components/center/search/university/CenterSearchUniversityInfo";

Vue.use(Router);

const router = new Router({
    mode: 'hash',
    routes: [
        // 这是一个测试页面, 一些效果方面的测试可以在此页面调试
        {
            path: '/test',
            name: 'test',
            component: Test
        },

        // 应用的主页
        {
            path: '/',
            name: 'index',
            component: Index,
        },

        // 应用的一个通用的消息页
        {
            path: '/info/:msg',
            name: 'Info',
            component: Info,
        },

        // 应用的auth页
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

        // 应用中心页
        {
            path: '/center',
            name: 'center',
            component: Center,
            meta: {
                keepAlive: true
            }
        },

        // 院校列表页
        {
            path: '/center-search-university',
            name: 'centerSearchUniversity',
            component: CenterSearchUniversity,
            meta: {
                keepAlive: true
            }
        },

        // 院校的详细信息页
        {
            path: '/center-search-university-info/:id',
            name: 'centerSearchUniversityInfo',
            component: CenterSearchUniversityInfo,
        },

        // 专业列表页
        {
            path: '/center-search-major',
            name: 'centerSearchMajor',
            component: CenterSearchMajor,
        },
    ]
});

export default router;