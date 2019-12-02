<!--
    @author : Graceyu
    @date   : 2019/12/1
    @status : develop

    @description:
        -> null.
-->
<template>
    <section id="index">
        <div class="index-background">
            <div class="background-before"></div>
            <div class="background-after" ref="backgroundAfter"></div>
        </div>
        <div class="index-marker"></div>
        <svg class="icon index-loading auto-rotation" aria-hidden="true" ref="indexLoading">
            <use xlink:href="#retUI-loading"></use>
        </svg>
        <div class="index-content" ref="indexContent">
            <transition name="effect-fromBottomToTop-opacity">
                <div class="content-buttons" v-if="contentButtonsDisplay">
                    <button @click="showLogin()" class="common-font">Log In</button>
                </div>
            </transition>
        </div>
        <transition name="effect-fromBottomToTop">
            <Login v-if="loginDisplay"></Login>
        </transition>
    </section>
</template>

<script>
    import Login from "@/components/auth/Login";
    export default {
        name: "Index",
        components: {Login},
        data() {
            return {
                contentButtonsDisplay: false,
                loginDisplay: false
            }
        },

        methods: {

            showLogin: function() {
                this.loginDisplay = true;
            }
        },

        mounted() {

            let image = new Image();
            image.src = "https://basic-images-1252577698.cos.ap-shenzhen-fsi.myqcloud.com/5.jpg";

            let _this = this;
            image.onload = function() {
                setTimeout(() => {
                    _this.$refs.backgroundAfter.style.backgroundImage = "url("+ image.src +")";
                    _this.$refs.backgroundAfter.style.opacity = "1";
                    _this.$refs.indexLoading.remove();
                    _this.contentButtonsDisplay = true;
                }, 1500)
            }
        }
    }
</script>

<style scoped lang="less">

    #index {
        width: 100%;
        height: 100%;
        position: relative;

        & > .index-background {
            width: inherit;
            height: inherit;
            position: absolute; top: 0; left: 0;
            z-index: 6;

            & > .background-before {
                width: inherit;
                height: inherit;
                position: absolute; top: 0; left: 0;
                background-color: rgba(0, 0, 0, 1);
                z-index: 7;
            }

            & > .background-after {
                width: inherit;
                height: inherit;
                position: absolute; top: 0; left: 0;
                background-repeat: no-repeat;
                background-position: center center;
                background-size: cover;
                z-index: 8;
                opacity: 0;

                transition: opacity 2666ms;
            }
        }

        & > .index-marker {
            width: inherit;
            height: inherit;
            position: absolute; top: 0; left: 0;
            background-color: rgba(0, 0, 0, 0.4);
            z-index: 9;
        }

        & > .index-loading {
            position: absolute; top: 10px; right: 10px;
            z-index: 10;
            font-size: 1.2rem;
            color: #fff;
        }

        & > .index-content {
            width: inherit;
            height: inherit;
            position: absolute; top: 0; left: 0;
            z-index: 11;

            & > .content-buttons {
                width: inherit;
                position: absolute; bottom: 80px; left: 0;
                text-align: center;

                & > button {
                    width: calc(120px + 15vw);
                    height: calc(30px + 2.5vh);
                    background-color: rgba(0, 0, 0, 0);
                    border: 1px solid rgba(255, 255, 255, 0.7);
                    color: #fff;
                    outline: none;
                    cursor: pointer;
                    border-radius: 50px;

                    transition: background-color 666ms, color 666ms;
                }

                & > button:hover {
                    background-color: rgba(255, 255, 255, 1);
                    color: #000;
                }
            }
        }
    }
</style>