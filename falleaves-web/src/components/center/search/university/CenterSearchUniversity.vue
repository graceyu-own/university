<template>
    <section id="center-search-university">

        <back-header></back-header>

        <div class="university-inner">

            <!-- 页面头部 -->
            <div class="inner-header">
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
                            <div class="item-content color-hover" @click="toggleSortMenuDisplay(null)">
                                <span>排序</span>
                                <i class="el-icon-caret-bottom"></i>
                            </div>

                            <!-- 条件-排序-菜单 -->
                            <div class="sort-menu" v-if="sortMenuDisplay" @click="toggleSortMenuDisplay(null)">
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
                            <div class="item-content" @click="toggleFilterMenuDisplay(null)">
                                <span>筛选</span>
                                <i class="el-icon-caret-bottom"></i>
                            </div>

                            <!-- 条件-过滤-菜单 -->
                            <div class="filter-menu" v-if="filterMenuDisplay" @click="toggleFilterMenuDisplay(null)">
                                <div class="menu-content" @click.stop="() => {}">
                                    <div class="content-body">
                                        <div class="body-section body-nature">
                                            <div class="section-title">
                                                <span>院校性质</span>
                                            </div>
                                            <el-row class="section-list" :gutter="10">
                                                <el-checkbox-group class="list-checkbox-group" v-model="universityNatureCheckbox" size="small">
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="1">双一流</el-checkbox-button>
                                                    </el-col>
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" type="warning" :label="2">985</el-checkbox-button>
                                                    </el-col>
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="3">211</el-checkbox-button>
                                                    </el-col>
                                                </el-checkbox-group>

                                            </el-row>
                                        </div>
                                        <div class="body-section body-tag">
                                            <div class="section-title">
                                                <span>院校标签</span>
                                            </div>
                                            <el-row class="section-list" :gutter="10">
                                                <el-checkbox-group class="list-checkbox-group" v-model="universityTagCheckbox" size="small">
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="1">知名院校</el-checkbox-button>
                                                    </el-col>
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="2">氛围好</el-checkbox-button>
                                                    </el-col>
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="3">环境好</el-checkbox-button>
                                                    </el-col>
                                                    <el-col class="group-item" :span="8">
                                                        <el-checkbox-button class="item-button" :label="4">环境好</el-checkbox-button>
                                                    </el-col>
                                                </el-checkbox-group>

                                            </el-row>
                                        </div>
                                        <div class="body-section body-location">
                                            <div class="section-title">
                                                <span>院校地区</span>
                                            </div>
                                            <el-row class="section-list" :gutter="10">
                                                <el-col class="list-item" :span="24" style="text-align: left;">
                                                    <el-button type="danger" icon="el-icon-search" size="small" style="" @click="openRegionSelector">{{locationSelectorName}}</el-button>
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
                <el-row class="body-layout" :gutter="20">

                    <!-- 院校卡片项 -->
                    <el-col class="layout-item" :span="layout.universityItem" v-for="(item, i) in paging.list" v-bind:key="i" @click.native="toUniversityInfo(item.id)">
                        <el-row class="item-layout">
                            <el-col class="layout-item layout-logo" :span="8">
                                <img :src="item.logo" alt="">
                            </el-col>
                            <el-col class="layout-item layout-content" :span="16">
                                <el-col class="content-title" :span="24">
                                    <span>{{item.name}}</span>
                                </el-col>
                                <el-col class="content-nature" :span="24">
                                    <el-tag size="mini" v-for="(nature, i) in item.nature" v-bind:key="i">{{nature}}</el-tag>
                                </el-col>
                                <el-col class="content-property" :span="24">
                                    <el-tag size="mini" type="warning">{{item.property.hot}}人气</el-tag>
                                    <el-tag size="mini" type="warning">{{item.property.score}}评分</el-tag>
                                </el-col>
                                <el-col class="content-tag" :span="24">
                                    <el-tag size="mini" type="success" v-for="(tag, i) in item.tag" v-bind:key="i">{{tag}}</el-tag>
                                </el-col>
                            </el-col>
                            <el-col class="layout-item layout-hotMajor" :span="24">
                                <el-col class="hotMajor-button" :span="layout.universityItemButton">
                                    <el-button size="mini" plain>软件工程</el-button>
                                </el-col>
                                <el-col class="hotMajor-button" :span="layout.universityItemButton">
                                    <el-button size="mini" plain>网络工程</el-button>
                                </el-col>
                                <el-col class="hotMajor-button" :span="layout.universityItemButton">
                                    <el-button size="mini" plain>建筑工程</el-button>
                                </el-col>
                                <el-col class="hotMajor-button" :span="layout.universityItemButton">
                                    <el-button size="mini" plain>我就是喜欢工程</el-button>
                                </el-col>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>

                <!-- 院校加载块(当滚动条滚动到底部时, 自动懒加载) -->
                <div class="body-load">
                    <div class="load-normal" v-if="!paging.isLast">
                        <i class="el-icon-loading"></i>
                    </div>
                    <div class="load-noData" v-if="paging.isLast">
                        <span> - 没有更多数据了 -</span>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
    import BackHeader from "@/components/common/header/BackHeader";

    export default {

        name: "CenterSearchUniversity",

        components: {BackHeader},

        data() {
            return {

                // 登录状态
                isLogin: this.$common.attr.has("token"),

                layout: {
                    universityItem: 12,
                    universityItemButton: 12
                },

                sortMenuDisplay: false,
                filterMenuDisplay: false,
                isPageLast: false,

                universityNatureCheckbox: [],
                universityTagCheckbox   : [],
                locationSelectorName    : "选择",

                paging: {
                    size        : 10,
                    now         : 1,
                    total       : 4,

                    isLast      : false,
                    isLoading   : false,

                    list        : [],
                }
            }
        },


        methods: {

            openRegionSelector: function() {
                this.$selector.region(2, (isReset, nav) => {
                    if(isReset) {
                        this.locationSelectorName = "选择";
                    } else {
                        if(nav.length === 3) {
                            this.locationSelectorName = nav[1].name + "-" + nav[2].name;
                        }
                    }
                });
            },

            toggleSortMenuDisplay: function(display = null) {
                if(null == display) {
                    this.sortMenuDisplay = !this.sortMenuDisplay;
                    this.toggleFilterMenuDisplay(false);
                } else {
                    this.sortMenuDisplay = display;
                }
            },

            toggleFilterMenuDisplay: function(display = null) {
                if(null == display) {
                    this.filterMenuDisplay = !this.filterMenuDisplay;
                    this.toggleSortMenuDisplay(false);
                } else {
                    this.filterMenuDisplay = display;
                }
            },

            pullUniversityData: function(page) {

                // todo 模拟数据
                for(let i = 0; i < 10; i++) {
                    this.paging.list.push({
                        id      : i,
                        logo    : "https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/728b4710b912c8fc95e9ae6efb039245d688217e.jpg",
                        name    : "北京大学",
                        nature  : ['双一流', '985', '211'],
                        property: {
                            hot     : page * (i + 1) * 1000,
                            score   : 9.9
                        },
                        tag     : ['氛围好']
                    });
                }
            },

            toUniversityInfo: function(id) {
                this.$store.state.university.bodyScrollTop = this.$refs.innerBody.scrollTop;
                this.$router.push("/center-search-university-info/" + id);
            }
        },

        activated() {
            this.$refs.innerBody.scrollTop = this.$store.state.university.bodyScrollTop;
        },

        mounted() {
            this.$refs.innerBody.addEventListener("scroll", () => {

                if(!this.paging.isLoading && !this.paging.isLast && this.$refs.innerBody.scrollHeight - this.$refs.innerBody.clientHeight - this.$refs.innerBody.scrollTop <= 50) {

                    // todo 尝试模拟加载数据
                    this.paging.isLoading = true;
                    setTimeout(() => {
                        this.pullUniversityData(++this.paging.now);
                        this.paging.isLoading = false;
                        if(this.paging.now >= this.paging.total) {
                            this.paging.isLast = true;
                        }
                    }, 2000);

                }
            });

            this.pullUniversityData(1);

            this.$common.watch.watchWindow((width) => {

                // 调整university button
                if(width <= 500) {
                    this.layout.universityItemButton = 24;
                } else if(width > 500 && width <= 1600) {
                    this.layout.universityItemButton = 12;
                } else {
                    this.layout.universityItemButton = 6;
                }

                // 调整university item
                if(width <= 600) {
                    this.layout.universityItem = 24;
                } else if(width > 600 && width <= 1400) {
                    this.layout.universityItem = 12;
                } else {
                    this.layout.universityItem = 8;
                }
            })
        }
    }
</script>

<style scoped lang="less">

    @import "../../../../assets/css/center";

    #center-search-university {
        width: 100%;
        height: 100%;
        background-color: rgba(255, 255, 255, 1);

        & > .university-inner {
            padding-top: 50px;
            height: 100%;

            & > .inner-header {
                height: auto;
                padding: 20px 20px 10px 20px;
                border-bottom: 1px solid rgba(45, 45, 45, 0.20);
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
                                z-index: var(--center--search--university-sort-menu);
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
                                z-index: var(--center--search--university-filter-menu);
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
                                                & > .list-checkbox-group {
                                                    & > .group-item {
                                                        margin-bottom: 10px;
                                                        & /deep/ * {
                                                            display: inline-block;
                                                            width: 100%;
                                                            text-align: center;
                                                            border-radius: 5px;
                                                        }
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
                padding: 20px;
                overflow-y: auto;

                & > .body-layout {

                    & > .layout-item {
                        & > .item-layout {
                            box-shadow: 0 0 10px rgba(47, 47, 47, 0.3);
                            padding: 10px;
                            border-radius: 5px;
                            cursor: pointer;
                            margin-bottom: 30px;
                            & > .layout-logo {
                                max-width: 120px;
                                margin-bottom: 10px;
                                & > img {
                                    width: 100%;
                                    height: 100%;
                                }
                            }
                            & > .layout-content {
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
                            & > .layout-hotMajor {
                                & > .hotMajor-button {
                                    padding: 5px;
                                    & > button {
                                        width: 100%;
                                    }
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