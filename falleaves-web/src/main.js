import Vue from 'vue'
import App from './App.vue'

import router from "@/assets/router";

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import Request from '@/assets/request';

Vue.config.productionTip = false;
Vue.use(ElementUI);

Vue.prototype.request = Request;


new Vue({
    render: h => h(App),
    router
}).$mount('#app');
