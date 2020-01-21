<template>
    <section id="center-search-major">

        <fl-header-default></fl-header-default>

        <div class="major-inner">

            <!-- 页面头部 -->
            <div class="inner-header" v-bind:class="{'box-shadow-bottom': headerSplit}">
                <el-row class="header-list">

                    <!-- 头部搜索框 -->
                    <el-col class="list-item list-search" :span="24">
                        <el-input class="search-input" placeholder="请输入内容">
                            <i slot="suffix" class="el-input__icon el-icon-search"></i>
                        </el-input>
                    </el-col>

                    <!-- 头部条件栏 -->
                    <el-col class="list-item list-condition" :span="24">

                        <!-- 条件-排序 -->
                        <el-col class="condition-item condition-sort" :span="12">

                            <!-- 条件-排序-按钮 -->
                            <div class="item-content color-hover" @click="ToggleSortMenuDisplay(null)">
                                <span>排序</span>
                                <i class="el-icon-caret-bottom"></i>
                            </div>

                            <!-- 条件-排序-菜单 -->
                            <div class="sort-menu" v-if="sortMenuDisplay" @click="ToggleSortMenuDisplay(null)">
                                <div class="menu-content" @click.stop="() => {}">
                                    <el-row class="content-list">
                                        <el-col class="list-item color-hover" :span="24">
                                            <el-col class="item-title" :span="18">
                                                <span>综合</span>
                                            </el-col>
                                            <el-col class="item-icon" :span="6">
                                                <i class="el-icon-check"></i>
                                            </el-col>
                                        </el-col>
                                        <el-col class="list-item" :span="24">
                                            <el-col class="item-title" :span="18">
                                                <span>人气升序</span>
                                            </el-col>
                                            <el-col class="item-icon" :span="6">
                                                <i class="el-icon-check"></i>
                                            </el-col>
                                        </el-col>
                                        <el-col class="list-item" :span="24">
                                            <el-col class="item-title" :span="18">
                                                <span>人气降序</span>
                                            </el-col>
                                            <el-col class="item-icon" :span="6">
                                                <i class="el-icon-check"></i>
                                            </el-col>
                                        </el-col>
                                        <el-col class="list-item" :span="24">
                                            <el-col class="item-title" :span="18">
                                                <span>评分升序</span>
                                            </el-col>
                                            <el-col class="item-icon" :span="6">
                                                <i class="el-icon-check"></i>
                                            </el-col>
                                        </el-col>
                                        <el-col class="list-item" :span="24">
                                            <el-col class="item-title" :span="18">
                                                <span>评分降序</span>
                                            </el-col>
                                            <el-col class="item-icon" :span="6">
                                                <i class="el-icon-check"></i>
                                            </el-col>
                                        </el-col>
                                    </el-row>
                                </div>
                            </div>

                        </el-col>

                        <!-- 条件-过滤 -->
                        <el-col class="condition-item condition-filter" :span="12">

                            <!-- 条件-过滤-按钮 -->
                            <div class="item-content" @click="ToggleFilterMenuDisplay(null)">
                                <span>筛选</span>
                                <i class="el-icon-caret-bottom"></i>
                            </div>

                            <!-- 条件-过滤-菜单 -->
                            <div class="filter-menu" v-if="filterMenuDisplay" @click="ToggleFilterMenuDisplay(null)">
                                <div class="menu-content" @click.stop="() => {}">
                                    <div class="content-body">
                                        <div class="body-section body-nature">
                                            <div class="section-title">
                                                <span>院校性质</span>
                                            </div>
                                            <el-row class="section-list" :gutter="10">
                                                <el-col class="list-item" :span="8">
                                                    <el-tag type="info">双一流</el-tag>
                                                </el-col>
                                                <el-col class="list-item" :span="8">
                                                    <el-tag type="info">985</el-tag>
                                                </el-col>
                                                <el-col class="list-item" :span="8">
                                                    <el-tag type="info">211</el-tag>
                                                </el-col>
                                            </el-row>
                                        </div>
                                        <div class="body-section body-location">
                                            <div class="section-title">
                                                <span>院校地区</span>
                                            </div>
                                            <el-row class="section-list" :gutter="10">
                                                <el-col class="list-item" :span="8">
                                                    <el-tag type="info">请选择</el-tag>
                                                </el-col>
                                            </el-row>
                                        </div>
                                    </div>
                                    <div class="content-footer">
                                        <el-row class="footer-list">
                                            <el-col class="list-item list-reset" :span="24">
                                                <el-button>重置所有条件</el-button>
                                            </el-col>
                                        </el-row>
                                    </div>
                                </div>
                            </div>
                        </el-col>
                    </el-col>
                </el-row>
            </div>

            <!-- 页面主体部分 -->
            <div class="inner-body" ref="innerBody">

                <!-- 院校卡片列表 -->
                <el-row class="body-list">

                    <!-- 院校卡片项 -->
                    <el-col class="list-item" :span="24">
                        <el-col class="item-logo" :span="8">
                            <img src="https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/728b4710b912c8fc95e9ae6efb039245d688217e.jpg" alt="">
                        </el-col>
                        <el-col class="item-content" :span="16">
                            <el-col class="content-title" :span="24">
                                <span>北京大学</span>
                            </el-col>
                            <el-col class="content-nature" :span="24">
                                <el-tag size="mini">双一流</el-tag>
                                <el-tag size="mini">985</el-tag>
                                <el-tag size="mini">211</el-tag>
                            </el-col>
                            <el-col class="content-property" :span="24">
                                <el-tag size="mini" type="warning">1425387 人气</el-tag>
                                <el-tag size="mini" type="warning">9.9 评分</el-tag>
                            </el-col>
                            <el-col class="content-tag" :span="24">
                                <el-tag size="mini" type="success">知名院校</el-tag>
                                <el-tag size="mini" type="success">氛围好</el-tag>
                            </el-col>
                        </el-col>
                        <el-col class="item-hotMajor" :span="24">
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>软件工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>网络工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>建筑工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>我就是喜欢工程</el-button>
                            </el-col>
                        </el-col>
                    </el-col>

                    <!-- 院校卡片项 -->
                    <el-col class="list-item" :span="24">
                        <el-col class="item-logo" :span="8">
                            <img src="https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/728b4710b912c8fc95e9ae6efb039245d688217e.jpg" alt="">
                        </el-col>
                        <el-col class="item-content" :span="16">
                            <el-col class="content-title" :span="24">
                                <span>北京大学</span>
                            </el-col>
                            <el-col class="content-nature" :span="24">
                                <el-tag size="mini">双一流</el-tag>
                                <el-tag size="mini">985</el-tag>
                                <el-tag size="mini">211</el-tag>
                            </el-col>
                            <el-col class="content-property" :span="24">
                                <el-tag size="mini" type="warning">1425387 人气</el-tag>
                                <el-tag size="mini" type="warning">9.9 评分</el-tag>
                            </el-col>
                            <el-col class="content-tag" :span="24">
                                <el-tag size="mini" type="success">知名院校</el-tag>
                                <el-tag size="mini" type="success">氛围好</el-tag>
                            </el-col>
                        </el-col>
                        <el-col class="item-hotMajor" :span="24">
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>软件工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>网络工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>建筑工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>我就是喜欢工程</el-button>
                            </el-col>
                        </el-col>
                    </el-col>

                    <!-- 院校卡片项 -->
                    <el-col class="list-item" :span="24">
                        <el-col class="item-logo" :span="8">
                            <img src="https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/728b4710b912c8fc95e9ae6efb039245d688217e.jpg" alt="">
                        </el-col>
                        <el-col class="item-content" :span="16">
                            <el-col class="content-title" :span="24">
                                <span>北京大学</span>
                            </el-col>
                            <el-col class="content-nature" :span="24">
                                <el-tag size="mini">双一流</el-tag>
                                <el-tag size="mini">985</el-tag>
                                <el-tag size="mini">211</el-tag>
                            </el-col>
                            <el-col class="content-property" :span="24">
                                <el-tag size="mini" type="warning">1425387 人气</el-tag>
                                <el-tag size="mini" type="warning">9.9 评分</el-tag>
                            </el-col>
                            <el-col class="content-tag" :span="24">
                                <el-tag size="mini" type="success">知名院校</el-tag>
                                <el-tag size="mini" type="success">氛围好</el-tag>
                            </el-col>
                        </el-col>
                        <el-col class="item-hotMajor" :span="24">
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>软件工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>网络工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>建筑工程</el-button>
                            </el-col>
                            <el-col class="hotMajor-button" :span="majorButtonSpan">
                                <el-button size="mini" plain>我就是喜欢工程</el-button>
                            </el-col>
                        </el-col>
                    </el-col>
                </el-row>

                <!-- 院校加载块(当滚动条滚动到底部时, 自动懒加载) -->
                <div class="body-load">
                    <div class="load-normal" v-if="false">
                        <i class="el-icon-loading"></i>
                    </div>
                    <div class="load-noData">
                        <span> - 没有更多数据了-</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>

    export default {

        name: "centerSearchMajor",
        data() {
            return {
                isLogin: this.attr.has("token"),
                headerSplit: false,

                majorButtonSpan: 12,

                sortMenuDisplay: false,
                filterMenuDisplay: false,

                screenWidth: document.documentElement.clientWidth, //屏幕宽度
                screenHeight: document.documentElement.clientHeight, //屏幕高度
            }
        },


        methods: {

            ToggleSortMenuDisplay: function(display = null) {
                if(null == display) {
                    this.sortMenuDisplay = !this.sortMenuDisplay;
                    this.ToggleFilterMenuDisplay(false);
                } else {
                    this.sortMenuDisplay = display;
                }
            },

            ToggleFilterMenuDisplay: function(display = null) {
                if(null == display) {
                    this.filterMenuDisplay = !this.filterMenuDisplay;
                    this.ToggleSortMenuDisplay(false);
                } else {
                    this.filterMenuDisplay = display;
                }
            },
        },

        watch: {
            screenWidth(val) {
                if(val <= 300) {
                    this.majorButtonSpan = 24;
                } else if(val <= 768) {
                    this.majorButtonSpan = 12;
                } else {
                    this.majorButtonSpan = 6;
                }
            }
        },

        mounted() {

            window.addEventListener("resize", () => {
                this.screenWidth = document.documentElement.clientWidth;
                this.screenHeight = document.documentElement.clientHeight;
            });


            this.$refs.innerBody.addEventListener("scroll", () => {
                this.headerSplit = this.$refs.innerBody.scrollTop >= 20;
            });
        }
    }
</script>

<style scoped lang="less">
    @import "../../../../assets/css/center";

    #center-search-major {
        width: 100%;
        height: 100%;
        background-color: rgba(255, 255, 255, 1);

        & > .major-inner {
            padding-top: 50px;
            height: 100%;

            & > .inner-header {
                height: auto;
                padding: 20px 20px 10px 20px;
                & > .header-list {
                    & > .list-search {
                        margin-bottom: 10px;
                    }
                    & > .list-condition {
                        padding: 6px 0;
                        & > .condition-item {
                            & > .item-content {
                                display: inline-block;
                                & > span {
                                    font-size: 1.4rem;
                                    margin-right: 10px;
                                }
                                & > i {
                                    color: rgba(47, 47, 47, 0.5);
                                }
                            }
                        }
                        & > .condition-sort {
                            text-align: left;
                            & > .sort-menu {
                                position: fixed;
                                left: 0;
                                z-index: var(--center--search--major-sort-menu);
                                width: 100%;
                                height: 100%;
                                max-height: calc(~"100vh - 145px");
                                overflow-y: auto;
                                background-color: rgba(0, 0, 0, 0.1);
                                & > .menu-content {
                                    padding: 10px 20px;
                                    background-color: rgba(255, 255, 255, 1);
                                    & > .content-list {
                                        border-top: 1px solid rgba(0, 0, 0, 0.05);
                                        & > .list-item {
                                            padding: 12px 0;
                                            border-bottom: 0.5px solid rgba(0, 0, 0, 0.15);
                                            & > .item-title {
                                                font-size: 1.4rem;
                                            }
                                            & > .item-icon {
                                                text-align: right;
                                                font-size: 1.5rem;
                                            }
                                        }
                                        & > .list-item:last-child {
                                            border-bottom: none;
                                        }
                                    }
                                }
                            }
                        }
                        & > .condition-filter {
                            text-align: right;
                            & > .filter-menu {
                                position: fixed;
                                top: 0;
                                left: 0;
                                width: 100%;
                                height: 100%;
                                background-color: rgba(47, 47, 47, 0.2);
                                z-index: var(--center--search--major-filter-menu);
                                & > .menu-content {
                                    position: absolute;
                                    right: 0;
                                    top: 0;
                                    width: 300px;
                                    max-width: 90%;
                                    height: 100%;
                                    overflow-y: auto;
                                    padding: 10px;
                                    background-color: rgba(255, 255, 255, 1);
                                    & > .content-body {
                                        padding: 30px 0;
                                        height: calc(~"100% - 50px");
                                        & > .body-section {
                                            margin-bottom: 20px;
                                            & > .section-title {
                                                text-align: left;
                                                font-size: 1.5rem;
                                                margin-bottom: 10px;
                                            }
                                            & > .section-list {
                                                & > .list-item {
                                                    & > span {
                                                        display: inline-block;
                                                        width: 100%;
                                                        text-align: center;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    & > .content-footer {
                                        padding: 0 10px;
                                        height: 50px;
                                        & > .footer-list {
                                            & > .list-reset {
                                                text-align: right;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            & > .inner-body {
                height: calc(~"100% - 110px");
                padding: 10px 20px 20px 20px;
                overflow-y: auto;

                & > .body-list {

                    & > .list-item {
                        box-shadow: 0 0 10px rgba(47, 47, 47, 0.3);
                        padding: 10px;
                        margin-bottom: 30px;
                        border-radius: 5px;
                        & > .item-logo {
                            max-width: 120px;
                            margin-bottom: 10px;
                            & > img {
                                width: 100%;
                                height: 100%;
                            }
                        }
                        & > .item-content {
                            padding-left: 20px;
                            & > div {
                                margin-bottom: 5px;
                            }
                            & > .content-title {
                                font-size: 2rem;
                                margin-bottom: 10px;
                            }
                            & > .content-nature {
                                & > span {
                                    display: inline-block;
                                    min-width: 40px;
                                    text-align: center;
                                    margin-right: 5px;
                                    margin-bottom: 5px;
                                }
                            }
                            & > .content-property {
                                & > span {
                                    display: inline-block;
                                    min-width: 40px;
                                    text-align: center;
                                    margin-right: 5px;
                                    margin-bottom: 5px;
                                }
                            }
                            & > .content-tag {
                                & > span {
                                    display: inline-block;
                                    min-width: 40px;
                                    text-align: center;
                                    margin-right: 5px;
                                    margin-bottom: 5px;
                                }
                            }
                        }
                        & > .item-hotMajor {
                            & > .hotMajor-button {
                                padding: 5px;
                                & > button {
                                    width: 100%;
                                }
                            }
                        }
                    }
                }

                & > .body-load {
                    & > .load-normal {
                        text-align: center;
                        & > i {
                            font-size: 2rem;
                        }
                    }
                    & > .load-noData {
                        text-align: center;
                        & > span {
                            font-size: 1.4rem;
                        }
                    }
                }
            }
        }
    }
</style>