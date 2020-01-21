<template>
    <section id="search-center">
        <div class="center-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>Search Center</span>
                </div>
            </div>
            <div class="inner-body" ref="innerBody">
                <el-row class="body-layout">
                    <!-- 热门搜索模块 -->
                    <el-col class="layout-item layout-hot" :span="24">
                        <el-row class="item-layout">
                            <el-col class="layout-item layout-title" :span="24">
                                <span>Hot Search</span>
                            </el-col>
                            <el-col class="layout-item layout-content">
                                <el-row class="content-layout" :gutter="15">
                                    <el-col class="layout-item" :span="layout.searchItem">
                                        <div class="item-inner">
                                            <el-row class="inner-layout">
                                                <el-col class="layout-item layout-left" :span="9">
                                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                                </el-col>
                                                <el-col class="layout-item layout-middle" :span="9">
                                                    <p class="middle-title">Institutional enquiry</p>
                                                    <p class="middle-msg">This is nstitutional enquiry</p>
                                                </el-col>
                                                <el-col class="layout-item layout-right" :span="6">
                                                    <button class="right-button" @click="toCenterSearchUniversity">Go</button>
                                                </el-col>
                                            </el-row>
                                        </div>
                                    </el-col>
                                    <el-col class="layout-item" :span="layout.searchItem">
                                        <div class="item-inner">
                                            <el-row class="inner-layout">
                                                <el-col class="layout-item layout-left" :span="9">
                                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                                </el-col>
                                                <el-col class="layout-item layout-middle" :span="9">
                                                    <p class="middle-title">Institutional enquiry</p>
                                                    <p class="middle-msg">This is nstitutional enquiry</p>
                                                </el-col>
                                                <el-col class="layout-item layout-right" :span="6">
                                                    <button class="right-button" @click="toCenterSearchUniversity">Go</button>
                                                </el-col>
                                            </el-row>
                                        </div>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                    </el-col>

                    <!-- 常用搜索模块 -->
                    <el-col class="layout-item layout-hot" :span="24">
                        <el-row class="item-layout">
                            <el-col class="layout-item layout-title" :span="24">
                                <span>Hot Search</span>
                            </el-col>
                            <el-col class="layout-item layout-content">
                                <el-row class="content-layout" :gutter="15">
                                    <el-col class="layout-item" :span="layout.searchItem">
                                        <div class="item-inner">
                                            <el-row class="inner-layout">
                                                <el-col class="layout-item layout-left" :span="9">
                                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                                </el-col>
                                                <el-col class="layout-item layout-middle" :span="9">
                                                    <p class="middle-title">Institutional Assessment</p>
                                                    <p class="middle-msg">This is institutional assessment</p>
                                                </el-col>
                                                <el-col class="layout-item layout-right" :span="6">
                                                    <button class="right-button">Go</button>
                                                </el-col>
                                            </el-row>
                                        </div>
                                    </el-col>
                                </el-row>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "centerSearch",

        data() {
            return {
                isLogin: this.$common.attr.has("token"),

                layout: {
                    searchItem: 24
                }
            }
        },

        methods: {
            toCenterSearchUniversity() {
                this.$router.push("/center-search-university");
            },

            toCenterSearchMajor() {
                this.$router.push("/center-search-major");
            }
        },

        mounted() {

            this.$common.watch.watchWindowSize((width) => {
                if(width <= 650) {
                    this.layout.searchItem = 24;
                } else if(width > 650 && width <= 1100) {
                    this.layout.searchItem = 12;
                } else if(width > 1100 && width <= 1500) {
                    this.layout.searchItem = 8;
                } else {
                    this.layout.searchItem = 6;
                }
            })

        }
    }
</script>

<style scoped lang="less">
    @import "../../../assets/css/center";

    #search-center {
        width: 100%;
        height: 100%;

        & > .center-inner {
            height: 100%;

            & > .inner-header {
                padding: 30px 20px;

                & > .header-title {
                    font-size: 2.7rem;
                }
            }

            & > .inner-body {
                height: calc(~"100% - 170px");
                padding: 0 20px;
                overflow-y: auto;

                & > .body-layout {
                    margin-bottom: 50px;

                    & > .layout-item {
                        & > .item-layout {
                            & > .layout-title {
                                font-size: 1.8rem;
                                margin-bottom: 15px;
                            }
                            & > .layout-content {
                                & > .content-layout {
                                    & > .layout-item {
                                        & > .item-inner {
                                            background-color: rgba(255, 255, 255, 1);
                                            box-shadow: 0 0 10px rgba(45, 45, 45, 0.15);
                                            border-radius: 5px;
                                            margin-bottom: 20px;
                                            & > .inner-layout {
                                                display: flex;
                                                & > .layout-left {
                                                    margin: auto;
                                                    padding: 10px;

                                                    & > .left-image {
                                                        width: 100%;
                                                        height: 100%;
                                                        border-radius: 5px;
                                                    }
                                                }
                                                & > .layout-middle {
                                                    padding: 10px;

                                                    & > .middle-title {
                                                        font-size: 1.6rem;
                                                        font-weight: bolder;
                                                    }

                                                    & > .middle-msg {
                                                        margin-top: 5px;
                                                        font-size: 1.2rem;
                                                        word-wrap: break-word;
                                                        word-break: break-all;
                                                        color: rgba(145, 142, 145, 1);
                                                    }
                                                }
                                                & > .layout-right {
                                                    margin: auto;
                                                    height: 100%;
                                                    padding: 10px 20px 10px 10px;

                                                    & > .right-button {
                                                        width: 100%;
                                                        height: 30px;
                                                        background-color: rgba(5, 5, 5, 1);
                                                        color: white;
                                                        border-radius: 2px;
                                                        border: none;
                                                        outline: none;
                                                        cursor: pointer;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                    & > .section-title {
                        font-size: 1.8rem;
                        margin-bottom: 15px;
                        //font-weight: 600;
                    }

                    & > .section-list {

                        & > .list-item {
                            background-color: rgba(255, 255, 255, 1);
                            box-shadow: 0 0 10px rgba(45, 45, 45, 0.15);
                            border-radius: 5px;
                            margin-bottom: 20px;

                            & > .item-row {
                                height: 100%;
                                display: flex;


                                & > .row-col {
                                    height: 100%;
                                }

                                & > .row-left {
                                    margin: auto;
                                    padding: 10px;

                                    & > .left-image {
                                        width: 100%;
                                        height: 100%;
                                        border-radius: 5px;
                                    }
                                }

                                & > .row-middle {
                                    padding: 10px;

                                    & > .middle-title {
                                        font-size: 1.6rem;
                                        font-weight: bolder;
                                    }

                                    & > .middle-msg {
                                        margin-top: 5px;
                                        font-size: 1.2rem;
                                        word-wrap: break-word;
                                        word-break: break-all;
                                        color: rgba(145, 142, 145, 1);
                                    }
                                }

                                & > .row-right {
                                    margin: auto;
                                    height: 100%;
                                    padding: 10px 20px 10px 10px;

                                    & > .right-button {
                                        width: 100%;
                                        height: 30px;
                                        background-color: rgba(5, 5, 5, 1);
                                        color: white;
                                        border-radius: 2px;
                                        border: none;
                                        outline: none;
                                        cursor: pointer;
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