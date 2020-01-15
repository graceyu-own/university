<template>
    <section id="common-photo-viewer" ref="common-photo-viewer">
        <div class="viewer-inner">
            <el-row class="inner-layout">
                <el-col class="layout-item layout-header" :span="24">
                    <div class="header-title">
                        <span>{{title}}</span>
                    </div>
                    <div class="header-close">
                        <i class="el-icon-close" @click="closeViewer"></i>
                    </div>
                </el-col>
                <el-col class="layout-item layout-body" :span="24">
                    <el-row class="body-layout">
                        <el-col class="layout-item" :span="24" v-for="(item, itemIndex) in list" v-bind:key="itemIndex">
                            <el-row class="item-layout">
                                <el-col class="layout-item layout-title" :span="24">
                                    <span>{{item.title}}</span>
                                </el-col>
                                <el-col class="layout-item layout-photo" :span="24">
                                    <el-row class="photo-layout" :gutter="10">
                                        <el-col class="layout-item" :span="layout.photoItem" v-for="(photo, photoIndex) in item.photo" v-bind:key="photoIndex">
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
                </el-col>
            </el-row>
        </div>
    </section>
</template>

<script>
    export default {
        name: "PhotoViewer",

        data() {
            return {

                layout: {
                    photoItem: 6,
                }

            }
        },

        methods: {
            closeViewer: function() {
                document.body.removeChild(this.$refs['common-photo-viewer']);
            },
        },

        mounted() {

            this.$common.watch.watchWindow((width) => {
                if (width <= 250) {
                    this.layout.photoItem = 24;
                }else if(width > 250 && width <= 400) {
                    this.layout.photoItem = 12;
                } else if (width > 400 && width <= 600) {
                    this.layout.photoItem = 8;
                } else if (width > 600 && width <= 800) {
                    this.layout.photoItem = 6;
                } else if (width > 800 && width <= 1000) {
                    this.layout.photoItem = 4;
                } else if (width > 1000 && width <= 1500) {
                    this.layout.photoItem = 3;
                } else if (width > 1500) {
                    this.layout.photoItem = 2;
                }
            })
        }
    }
</script>

<style scoped lang="less">

    #common-photo-viewer {
        display: flex;
        width: 100%;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        z-index: var(--common--viewer--photo-viewer);
        background-color: rgba(45, 45, 45, 0.3);
        & > .viewer-inner {
            width: 100%;
            height: 80%;
            max-height: 500px;
            background-color: rgba(255, 255, 255, 1);
            border-radius: 10px 10px 0 0;
            position: absolute;
            bottom: 0;
            left: 0;
            overflow-y: hidden;
            & > .inner-layout {
                height: 100%;
                & > .layout-header {
                    padding: 20px;
                    font-size: 1.7rem;
                    border-bottom: 0.5px solid rgba(0, 0, 0, 0.2);
                    & > .header-title {

                    }
                    & > .header-close {
                        position: absolute;
                        right: 20px;
                        top: 20px;
                        font-size: 1.6rem;
                        cursor: pointer;
                    }
                }
                & > .layout-body {
                    height: calc(~"100% - 62.5px");
                    padding: 20px;
                    overflow-y: auto;
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
    }
</style>