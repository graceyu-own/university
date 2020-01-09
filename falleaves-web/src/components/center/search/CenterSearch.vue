<template>
    <section id="search-center">
        <div class="center-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>Search Center</span>
                </div>
            </div>
            <div class="inner-body" ref="innerBody">
                <div class="body-section body-hot">
                    <div class="section-title">
                        <span>Hot Search</span>
                    </div>
                    <el-row class="section-list">
                        <el-col :xs="{span: 24, offset: 0}" :sm="{span: 10, offset: 1}" :md="{span: 10, offset: 1}" :lg="{span: 6, offset: 1}" class="list-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="9">
                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                </el-col>
                                <el-col class="row-col row-middle" :span="9">
                                    <p class="middle-title">Institutional enquiry</p>
                                    <p class="middle-msg">This is nstitutional enquiry</p>
                                </el-col>
                                <el-col class="row-col row-right" :span="6">
                                    <button class="right-button" @click="ToCenterSearchUniversity">Go</button>
                                </el-col>
                            </el-row>
                        </el-col>
                        <el-col :xs="{span: 24, offset: 0}" :sm="{span: 10, offset: 1}" :md="{span: 10, offset: 1}" :lg="{span: 6, offset: 1}" class="list-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="9">
                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                </el-col>
                                <el-col class="row-col row-middle" :span="9">
                                    <p class="middle-title">Professional enquiry</p>
                                    <p class="middle-msg">This is professional enquiry</p>
                                </el-col>
                                <el-col class="row-col row-right" :span="6">
                                    <button class="right-button" @click="ToCenterSearchMajor">Go</button>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </div>
                <div class="body-section body-other">
                    <div class="section-title">
                        <span>Other Search</span>
                    </div>
                    <el-row class="section-list">
                        <el-col :xs="{span: 24, offset: 0}" :sm="{span: 10, offset: 1}" :md="{span: 10, offset: 1}" :lg="{span: 6, offset: 1}" class="list-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="9">
                                    <img class="left-image" src="../../../../public/images/5.jpg" alt="" />
                                </el-col>
                                <el-col class="row-col row-middle" :span="9">
                                    <p class="middle-title">Institutional Assessment</p>
                                    <p class="middle-msg">This is institutional assessment</p>
                                </el-col>
                                <el-col class="row-col row-right" :span="6">
                                    <button class="right-button">Go</button>
                                </el-col>
                            </el-row>
                        </el-col>
                    </el-row>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CenterSearch",

        data() {
            return {
                isLogin: this.attr.has("token"),
                headerSplit: false
            }
        },

        methods: {
            ToCenterSearchUniversity() {
                this.$router.push("/center-search-university");
            },

            ToCenterSearchMajor() {
                this.$router.push("/center-search-major");
            }
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

                & > .body-section {
                    margin-bottom: 50px;

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