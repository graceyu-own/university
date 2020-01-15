import Vue from 'vue';
import RegionSelector from "./src/RegionSelector";

const RegionSelectorConstructor = Vue.extend(RegionSelector);

/**
 *
 * @param level     最大选择层级(-1为无限)
 * @param callback  回调函数
 *  -> isReset: 是否是重置操作
 *  -> nav    : 被选择的导航列表
 * @returns
 */
function showRegionSelector(level = 3, callback = () => {}) {
    const regionSelectorDOM = new RegionSelectorConstructor({
        el: document.createElement("div"),
        data() {
            return {
                level: level
            }
        },

        methods: {

            /**
             * 筛选器的回调函数(在筛选完毕后执行, 而非关闭筛选器)
             */
            callback: callback
        }
    });
    document.body.appendChild(regionSelectorDOM.$el)
}

function registry() {
    Vue.prototype.$selector = {
        region: showRegionSelector
    }
}

export default registry