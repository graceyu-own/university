import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const state = {
    university: {
        bodyScrollTop: 0
    }
};

export default new Vuex.Store({
    state
});