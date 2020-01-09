<template>
    <section id="center-search-university-info">

        <back-header></back-header>

        <div class="info-inner">
            <el-row class="inner-list">
                <el-col class="list-item list-header" :span="24">
                    <el-col class="item-logo" :span="8">
                        <img src="https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/728b4710b912c8fc95e9ae6efb039245d688217e.jpg" alt="">
                        <i class="el-icon-copy-document"></i>
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
                </el-col>
                <el-col class="list-item list-info" :span="24">
                    <el-collapse class="info-collapse" accordion>
                        <el-collapse-item class="collapse-item" title="院校信息" name="1">
                            <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
                            <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
                        </el-collapse-item>
                        <el-collapse-item class="collapse-item" title="院校概况" name="2">
                            <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
                            <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
                        </el-collapse-item>
                    </el-collapse>
                </el-col>
                <el-col class="list-item list-tab" :span="24">
                    <el-tabs class="tab-list" type="border-card" v-model="tabActiveName" @tab-click="TabHandle">
                        <el-tab-pane class="list-item list-info" label="招生简章" name="info">无数据</el-tab-pane>
                        <el-tab-pane class="list-item list-score" label="历年分数线" name="score">
                            <div class="" id="a" style="width: 100%; height: 300px;"></div>
                        </el-tab-pane>
                        <el-tab-pane class="list-item list-major" label="招生专业" name="major">暂无数据</el-tab-pane>
                    </el-tabs>
                </el-col>
            </el-row>
        </div>
    </section>
</template>

<script>
    import BackHeader from "@/components/common/header/BackHeader";

    export default {
        name: "CenterSearchUniversityInfo",
        components: {BackHeader},

        data() {
            return {
                tabActiveName: "score",
            }
        },

        methods: {

            TabHandle: function(tab, event) {
                this.InitScore();
            },

            InitScore: function() {
                this.$chart.UniversityScoreCharts(document.getElementById("a"));
            }
        },

        mounted() {

            this.InitScore();

        }

    }
</script>

<style scoped lang="less">

    #center-search-university-info {
        width: 100%;
        height: 100%;
        background-color: rgba(255, 255, 255, 1);
        & > .info-inner {
            height: 100%;
            padding-top: 50px;
            & > .inner-list {
                & > .list-item {
                    margin-top: 20px;
                    & > .item-title {
                        font-size: 1.6rem;
                        margin-bottom: 10px;
                    }
                }
                & > .list-header {
                    padding: 10px 20px;
                    border-radius: 5px;
                    & > .item-logo {
                        max-width: 120px;
                        margin-bottom: 10px;
                        position: relative;
                        & > img {
                            width: 100%;
                            height: 100%;
                        }
                        & > i {
                            position: absolute;
                            right: 0;
                            bottom: 0;
                            font-size: 2rem;
                            background-color: rgba(47, 47, 47, 0.6);
                            padding: 10px;
                            color: white;
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
                }

                & > .list-info {
                    padding: 10px;
                    & > .info-collapse {
                        & > .collapse-item {
                        }
                    }
                }

                & > .list-tab {
                    padding: 10px;
                }
            }
        }
    }
</style>