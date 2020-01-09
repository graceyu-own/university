import Vue from 'vue';
import App from './App.vue';

// element-ui imports
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';


// custom component imports
import request from '@/assets/js/common/request';
import router from "@/assets/js/common/router";
import store from '@/assets/js/vuex/store';
import charts from "@/assets/js/common/charts";
import common from '@/assets/js/common/common'

// packages
import selector from '@/packages/selector/selector'
Vue.use(selector);


Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(charts);
Vue.use(request);
Vue.use(common);


new Vue({
    render: h => h(App),
    store,
    router
}).$mount('#app');
