import Vue from 'vue';
import Header from './src/main';
import HeaderDefault from './src/extend/default';
import HeaderSimple from './src/extend/simple';

function registry() {
    Vue.component(Header.name, Header);
    Vue.component(HeaderDefault.name, HeaderDefault);
    Vue.component(HeaderSimple.name, HeaderSimple);
}

export default registry;