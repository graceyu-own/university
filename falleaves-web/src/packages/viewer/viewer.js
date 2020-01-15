import Vue from 'vue'
import PhotoViewer from "@/packages/viewer/src/PhotoViewer";

const PhotoViewerConstructor = Vue.extend(PhotoViewer);

/**
 *
 * @param title     菜单标题
 * @param list      菜单图片列表
 *
 * @returns
 */
function showPhotoViewer(title = "Photos", list = []) {
    const photoViewerDOM = new PhotoViewerConstructor({
        el: document.createElement("div"),
        data() {
            return {
                title: title,

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
    document.body.appendChild(photoViewerDOM.$el);
}

function registry() {
    Vue.prototype.$viewer = {
        photo: showPhotoViewer
    }
}

export default registry;