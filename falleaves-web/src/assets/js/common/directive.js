import Vue from 'vue'

class ElementPull {

    constructor() {

        Vue.directive('pull', {

            inserted: (el, binding) => {

                let startY = 0;
                let middleY = 0;
                let endY = 0;

                let topElement = el.querySelectorAll(binding.value.bindTop)[0];

                el.addEventListener("touchstart", (e) => {
                    startY = e.touches[0].pageY;
                    console.log(startY)
                });

                el.addEventListener("touchmove", (e) => {
                    //if (this.isTop(el) && (e.touches[0].pageY - startY) >= 0) {
                        middleY = e.touches[0].pageY;
                        console.log(middleY - startY);

                        topElement.style.height = parseInt(Math.sqrt(middleY - startY) * 6.48) + "px";
                   // }
                });

                el.addEventListener("touchend", (e) => {
                    topElement.style.height = "0" + "px";
                })
            }
        })
    }

    isTop(element) {
        return element.scrollTop === 0;
    }

}

function registry() {
    new ElementPull();
}

export default registry;