<template>
    <section class="fl-viewer-photo" :ref="'fl-viewer-photo-' + uniqueId" @click="closeViewer">
        <transition name="effect-fromBottomToTop">
            <el-container class="photo-container" v-loading="loading" ref="inner" v-if="show" @click.native.stop="() => {}">
                <el-header class="container-header">
                    <div class="header-title">
                        <p>SELECTOR</p>
                    </div>
                    <div class="header-button">
                        <i class="el-icon-close" @click="closeViewer"></i>
                    </div>
                </el-header>
                <el-main class="container-body">
                    <el-row class="body-layout">
                        <el-col class="layout-item" :span="24" v-for="(item, itemIndex) in list" v-bind:key="itemIndex">
                            <el-row class="item-layout">
                                <el-col class="layout-item layout-title" :span="24">
                                    <span>{{item.title}}</span>
                                </el-col>
                                <el-col class="layout-item layout-photo" :span="24">
                                    <el-row class="photo-layout" :gutter="10">
                                        <el-col class="layout-item" :span="layout.values[layout.index]" v-for="(photo, photoIndex) in item.photo" v-bind:key="photoIndex" ref="test">
                                            <el-image class="item-image" :src="photo" :preview-src-list="item.photo" :fit="'cover'">
                                                <div slot="error" class="image-slot">
                                                    <i class="el-icon-picture-outline"></i>
                                                </div>
                                                <div slot="placeholder" class="image-slot">
                                                    <span class="dot el-icon-loading"></span>
                                                </div>
                                            </el-image>
                                        </el-col>
                                    </el-row>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </el-main>
            </el-container>
        </transition>
    </section>
</template>

<script>
    export default {
        name: "FlViewerPhoto",

        data() {
            return {

                show: false,
                loading: false,

                layout: {
                    values: [24, 12, 8, 6, 4, 3, 2, 1],
                    index: 0,
                }

            }
        },

        methods: {
            closeViewer: function() {
                this.show = false;
                setTimeout(() => {
                    this.parent.removeChild(this.$refs['fl-viewer-photo-' + this.uniqueId]);
                }, 666);

            },
        },

        mounted() {

            this.$common.watch.watchWindowSize((width) => {
                if (width <= 250) {
                    this.layout.index = 0;
                }else if(width > 250 && width <= 400) {
                    this.layout.index = 1;
                } else if (width > 400 && width <= 600) {
                    this.layout.index = 2;
                } else if (width > 600) {
                    this.layout.index = 3;
                }
            });

            this.show = true;
        }
    }
</script>

<style scoped lang="less">

    .fl-viewer-photo {
        display: flex;
        flex-direction: column-reverse;
        width: 100%;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        z-index: var(--common-viewer-photo--main);
        background-color: rgba(45, 45, 45, 0.3);

        @media screen and (max-width: 600px) {
            & > .photo-container {
                margin: 0 auto;
                border-radius: 10px 10px 0 0;
            }
        }

        @media screen and (min-width: 600px) {
            & > .photo-container {
                margin: auto;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }
        }

        & > .photo-container {
            width: 100%;
            max-width: 600px;
            height: 80%;
            max-height: 500px;
            background-color: rgba(255, 255, 255, 1);
            overflow: hidden;

            & > .container-header {
                width: 100%;
                padding: 20px;
                border-bottom: 0.5px solid rgba(0, 0, 0, 0.3);
                font-size: 1.6rem;
                text-align: center;
                position: relative;

                & > .header-title {

                }

                & > .header-button {
                    position: absolute;
                    right: 20px;
                    top: 20px;
                    font-size: 1.6rem;
                    cursor: pointer;

                    & > i {
                        font-weight: bolder;
                    }
                }
            }

            & > .container-body {
                & > .body-layout {
                    & > .layout-item:last-child {
                        margin-bottom: 0;
                    }

                    & > .layout-item {
                        margin-bottom: 15px;

                        & > .item-layout {
                            & > .layout-title {
                                font-size: 1.5rem;

                                & > span {
                                    padding: 0 2px 2px 2px;
                                    border-bottom: 2px solid rgba(0, 0, 0, 1);
                                }
                            }

                            & > .layout-photo {
                                margin-top: 15px;

                                & > .photo-layout {
                                    & > .layout-item {
                                        margin-bottom: 10px;

                                        & > .item-image {
                                            height: 70px;
                                            border-radius: 10px !important;

                                            & > img {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
</style>