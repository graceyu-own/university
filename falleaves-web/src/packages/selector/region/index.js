import Vue from 'vue';
import SelectorRegion from "./src/main";

const SelectorRegionConstructor = Vue.extend(SelectorRegion);

/**
 * @param parent    需要插入到的对象
 * @param level     最大选择层级(-1为无限)
 * @param callback  回调函数
 *  -> isReset: 是否是重置操作
 *  -> nav    : 被选择的导航列表
 * @returns
 */
function region(parent = document.body, level = 3, callback = () => {}) {
    const dom = new SelectorRegionConstructor({
        el: document.createElement("div"),
        data() {
            return {
                level: level,
                uniqueId: this.$common.uniqueIdManager.next(),
                parent: parent,
            }
        },

        methods: {

            /**
             * 筛选器的回调函数(在筛选完毕后执行, 而非关闭筛选器)
             */
            callback: callback
        }
    });
    if(null != parent && parent instanceof HTMLElement) {
        parent.appendChild(dom.$el)
    }
}

export default function registry() {
    Vue.prototype.$selector = {
        region: region
    }
}