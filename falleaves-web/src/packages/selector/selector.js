import Vue from 'vue';
import RegionSelector from "./src/RegionSelector";

const RegionSelectorConstructor = Vue.extend(RegionSelector);

function showRegionSelector(level = 1, callback = () => {}) {
    const regionSelectorDOM = new RegionSelectorConstructor({
        el: document.createElement("div"),
        data() {
            return {
                /**
                 * 地区筛选器筛选层级
                 *     * If level is 1: select only first layer
                 */
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