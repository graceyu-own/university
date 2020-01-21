import Vue from 'vue';
import App from './App.vue';

// element-ui imports
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

// vue-router imports
import router, {routerMethods} from '@/assets/js/common/router';
routerMethods.Init();

// custom component imports
import request from '@/assets/js/common/request';
import store from '@/assets/js/vuex/store';
import charts from "@/assets/js/common/charts";
import common from '@/assets/js/common/common';
import directive from "@/assets/js/common/directive";
Vue.use(common);
Vue.use(request);
Vue.use(charts);
Vue.use(directive);

// packages
import selector from '@/packages/selector/index';
Vue.use(selector);

import viewer from '@/packages/viewer/index';
Vue.use(viewer);

import header from '@/packages/header/index';
Vue.use(header);

import navigation from '@/packages/navigation/index';
Vue.use(navigation);

// todo 1. 整顿一下selector和viewer, 让他们保证在packages中处于同一种实现模式
// todo 2. 在center-user-info页的头部, 改变header颜色的延迟较大, 需修正
// 3. 继续规范编码格式(包括但不限于: 文件名规范、标签定义规范、Js方法名规范

// EX1: 还有缓存的问题, 在selector或者viewer打开后, 如果直接切换页面, 再次切回去会因为缓存的问题保存下来, 因此需要做一项判断, 而且由前至后不进行缓存, 由后回前进行缓存
// EX2: 排序选择器还没有完善, 条件过滤器也没有完善
// EX3: 院校信息页还没有完善, 等待数据库完善
// EX4: 用户中心页, Settings等列项没有写

Vue.config.productionTip = false;


new Vue({
    render: h => h(App),
    store,
    router
}).$mount('#app');
