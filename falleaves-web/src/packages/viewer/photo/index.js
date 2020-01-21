import Vue from 'vue'
import ViewerPhoto from "@/packages/viewer/photo/src/main";

const ViewerPhotoConstructor = Vue.extend(ViewerPhoto);

/**
 * @param parent    需要插入到的对象
 * @param title     菜单标题
 * @param list      菜单图片列表
 *
 * @returns
 */
function photo(parent = document.body, title = "Photos", list = []) {
    const dom = new ViewerPhotoConstructor({
        el: document.createElement("div"),
        data() {
            return {
                title: title,
                uniqueId: this.$common.uniqueIdManager.next(),
                parent: parent,

                /**
                 *  数据格式类型
                 *  [
                 *      {
                 *          title: "...",
                 *          photo: {
                 *              "photo url-1",
                 *              "photo url-2"
                 *          }
                 *      },
                 *      {
                 *          title: "...",
                 *          photo: {
                 *              "photo url-1",
                 *              "photo url-2"
                 *          }
                 *      }
                 *  ]
                 */
                list: list
            }
        }
    });
    if(null != parent && parent instanceof HTMLElement) {
        parent.appendChild(dom.$el)
    }
}

export default function registry() {
    Vue.prototype.$viewer = {
        photo: photo
    }
}