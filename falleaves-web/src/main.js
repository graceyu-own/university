import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import Request from '@/assets/request';
import Attr from '@/assets/attr'
import router from "@/assets/router";


Vue.config.productionTip = false;
Vue.use(ElementUI);

Vue.prototype.request = Request;
Vue.prototype.attr = Attr;
new Vue({
    render: h => h(App),
    router
}).$mount('#app');
