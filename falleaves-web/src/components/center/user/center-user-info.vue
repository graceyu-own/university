<template>
    <section id="center-user-info">
        <fl-header class="info-header" :bgColor="bgColor">
            <span slot="left" class="header-back el-icon-arrow-left" v-routers:back></span>
            <span slot="center" v-if="showCenter">我的信息</span>
            <span slot="right">更多</span>
        </fl-header>
        <div class="info-inner" ref="info-inner">
            <el-row class="inner-layout">
                <el-col class="layout-item layout-background" :span="24">
                    <div class="background-content"></div>
                    <div class="background-mask"></div>
                </el-col>
                <el-col class="layout-item layout-avatar" :span="24">
                    <el-avatar shape="square" :fit="'fill'" :size="110" :src="'/images/test_avatar.png'"></el-avatar>
                </el-col>
                <el-col class="layout-item layout-name" :span="24">
                    <p class="name-title">GraceYu</p>
                </el-col>
                <el-col class="layout-item layout-info" :span="24">
                    <el-row class="info-layout">
                        <el-col class="layout-item layout-description" :span="24">
                            <span class="item-title">简介</span>
                            <p class="item-content">I paid everything, I got anything?</p>
                        </el-col>
                        <el-col class="layout-item layout-basic" :span="24">
                            <span class="item-title">基本信息</span>
                            <ul class="item-content ul-list">
                                <li class="list-item"><i class="el-icon-star-off item-icon-left"></i><span class="item-title">ID</span><span class="item-msg">10001</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                                <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title">邮箱</span><span class="item-msg">g@graceyu.top</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                                <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title">手机号</span><span class="item-msg">18779619752</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                            </ul>
                        </el-col>
                        <el-col class="layout-item layout-extend" :span="24">
                            <span class="item-title">更多信息</span>
                            <ul class="item-content ul-list">
                                <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title">地区</span><span class="item-msg">广东省-深圳市</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                                <li class="list-item"><i class="el-icon-star-off item-icon-left"></i><span class="item-title">出生日期</span><span class="item-msg">1999-07-24</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                            </ul>
                        </el-col>
                        <el-col class="layout-item layout-tag" :span="24">
                            <span class="item-title">个性标签</span>
                            <div class="item-content">
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                                <el-tag>标签一</el-tag>
                            </div>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </div>
    </section>
</template>

<script>
    export default {
        name: "centerUserInfo",

        data() {
            return {
                bgColor: 'none',
                showCenter: false,
            }
        },

        methods: {

        },

        mounted() {

            this.$common.watch.watchElementScroll(this.$refs['info-inner'], (top) => {
                if(top >= 50) {
                    this.bgColor = "rgba(0, 0, 0, 1)";
                    this.showCenter = true;
                } else {
                    this.bgColor = "rgba(0, 0, 0, 0)";
                    this.showCenter = false;
                }
            }, 10);
        }
    }
</script>

<style scoped lang="less">

    @import "../../../assets/css/center";

    #center-user-info {
        width: 100%;
        height: 100%;
        & > .info-header {
            & .header-back {
                font-weight: bolder;
                font-size: 1.7rem;
            }
        }
        & > .info-inner {
            width: 100%;
            height: 100%;
            overflow-y: auto;
            & > .inner-layout {
                & > .layout-background {
                    height: 200px;
                    position: relative;
                    //background-color: rgba(227, 179, 255, 1)
                    & > .background-content {
                        position: absolute;
                        top: 0;
                        left: 0;
                        width: 100%;
                        height: 100%;
                        z-index: 5;
                        background-image: url("../../../../public/images/test_background.png");
                        background-position: top center;
                        background-size: cover;
                    }
                    & > .background-mask {
                        position: absolute;
                        top: 0;
                        left: 0;
                        width: 100%;
                        height: 100%;
                        z-index: 6;
                        background-color: rgba(0, 0, 0, 0.20);
                    }

                }
                & > .layout-avatar {
                    position: relative;
                    z-index: 7;
                    padding: 15px;
                    transform: translateY(-50%);
                    text-align: center;
                    & > span {
                        box-shadow: 0 0 10px rgba(47, 47, 47, 0.2);
                        padding: 3px;
                        background-color: white;
                    }
                }
                & > .layout-name {
                    margin-top: -60px;
                    & > .name-title {
                        font-size: 1.8rem;
                        text-align: center;
                    }
                }
                & > .layout-info {
                    & > .info-layout {
                        & > .layout-item {
                            padding: 15px;
                            & > .item-title {
                                font-size: 1.7rem;
                                padding: 0 2px 3px 2px;
                                border-bottom: 2px solid rgba(0, 0, 0, 1);
                            }
                            & > .item-content {
                                margin-top: 15px;
                            }
                        }
                        & > .layout-description {
                            & > .item-content {
                                word-break: break-all;
                                word-wrap: break-word;
                                font-size: 1.4rem;
                            }
                        }
                        & > .layout-tag {
                            & > .item-content {
                                & > span {
                                    margin-right: 10px;
                                    margin-bottom: 10px;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
</style>