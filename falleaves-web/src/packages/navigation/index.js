import Vue from 'vue'

import Navigation from './src/main';

function registry() {
    Vue.component(Navigation.name, Navigation);
}

export default registry;