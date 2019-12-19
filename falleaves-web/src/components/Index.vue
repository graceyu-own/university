<template>
    <section id="index" ref="index">
        <div class="index-background">
            <div class="background-before"></div>
            <div class="background-after" ref="backgroundAfter"></div>
        </div>
        <div class="index-mask"></div>
        <svg class="icon index-loading auto-rotation" aria-hidden="true" ref="loading">
            <use xlink:href="#retUI-loading"></use>
        </svg>
        <div class="index-content">
            <transition name="effect-fromBottomToTop-opacity">
                <div class="content-buttons" v-if="contentButtonsDisplay">
                    <el-row>
                        <el-col :span="24"><button @click="ToLogin()">Log In</button></el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24"><button @click="ToHome()">HOME</button></el-col>
                    </el-row>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>

    export default {
        name: "Index",


        data() {
            return {

                contentButtonsDisplay: false,
                loginDisplay: false
            }
        },

        methods: {

            ToLogin: function() {
                this.$router.push("/auth/login");
            },

            ToHome: function() {
                this.$router.push("/center");
            },

            Init: function() {

                let image = new Image();
                image.src = "https://basic-images-1252577698.cos.ap-shenzhen-fsi.myqcloud.com/5.jpg";


                let _this = this;
                image.onload = function() {
                    setTimeout(() => {
                        _this.$refs.backgroundAfter.style.backgroundImage = "url("+ image.src +")";
                        _this.$refs.backgroundAfter.style.opacity = "1";
                        _this.$refs.loading.remove();
                        _this.contentButtonsDisplay = true;
                    }, 0)
                }
            }
        },

        mounted() {

            this.Init();
        },
    }
</script>

<style scoped lang="less">

    #index {
        width: 100%;
        height: 100%;
        position: relative;

        transition: width 666ms, height 666ms, margin-top 666ms, margin-left 666ms;

        & > .index-background {

            width: 100%;
            height: 100%;
            position: absolute; top: 0; left: 0;

            z-index: 6;

            & > .background-before {
                width: 100%;
                height: 100%;
                position: absolute; top: 0; left: 0;
                background-color: rgba(0, 0, 0, 1);
                z-index: 7;
            }

            & > .background-after {
                width: 100%;
                height: 100%;
                position: absolute; top: 0; left: 0;
                background-repeat: no-repeat;
                background-position: center center;
                background-size: cover;
                z-index: 8;
                opacity: 0;

                transition: opacity 2666ms;
            }
        }

        & > .index-mask {
            width: 100%;
            height: 100%;
            position: absolute; top: 0; left: 0;
            background-color: rgba(0, 0, 0, 0.4);
            z-index: 9;
        }

        & > .index-loading {
            position: absolute; top: 10px; right: 10px;
            z-index: 10;
            font-size: 2.4rem;
            color: #fff;
        }

        & > .index-content {
            width: 100%;
            height: 100%;
            position: absolute; top: 0; left: 0;

            z-index: 11;

            & > .content-buttons {
                width: inherit;
                position: absolute; bottom: 80px; left: 0;
                text-align: center;

                & > div {
                    margin-top: 20px;
                }

                &  button {
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

                &  button:hover {
                    background-color: rgba(255, 255, 255, 1);
                    color: #000;
                }
            }
        }

        & > .index-popupMarker {
            width: inherit;
            height: inherit;
            position: absolute; top: 0; left: 0;
            z-index: 12;
            background-color: rgba(255, 255, 255, 0.6);
        }
    }
</style>