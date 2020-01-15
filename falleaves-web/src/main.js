import Vue from 'vue';
import App from './App.vue';

// element-ui imports
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// custom component imports
import request from '@/assets/js/common/request';
import router from "@/assets/js/common/router";
import store from '@/assets/js/vuex/store';
import charts from "@/assets/js/common/charts";
import common from '@/assets/js/common/common'
Vue.use(common);
Vue.use(request);
Vue.use(charts);

// packages
import selector from '@/packages/selector/selector'
Vue.use(selector);

import viewer from '@/packages/viewer/viewer'
Vue.use(viewer);

Vue.config.productionTip = false;


new Vue({
    render: h => h(App),
    store,
    router
}).$mount('#app');
