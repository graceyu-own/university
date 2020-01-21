<template>
    <section id="center-notify">
        <div class="notify-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>Notification Center</span>
                </div>
            </div>
            <div class="inner-body" ref="innerBody">
                <section class="body-section">
                    <div class="section-date">
                        <h5>2019 / 12 / 30 17:03:25</h5>
                    </div>
                    <div class="section-content">
                        <img class="content-image" src="../../../../public/images/5.jpg" alt="" />
                        <div class="content-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="4">
                                    <i class="el-icon-search"></i><br />
                                    <span></span>
                                </el-col>
                                <el-col class="row-col row-right" :span="20">
                                    <p class="right-title">Good looking fonts</p>
                                    <p class="right-msg">New flat style makes the page more beautiful.</p>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </section>
                <section class="body-section">
                    <div class="section-date">
                        <h5>2019 / 12 / 30 17:03:25</h5>
                    </div>
                    <div class="section-content">
                        <img class="content-image" src="../../../../public/images/5.jpg" alt="" />
                        <div class="content-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="4">
                                    <i class="el-icon-search"></i><br />
                                    <span></span>
                                </el-col>
                                <el-col class="row-col row-right" :span="20">
                                    <p class="right-title">Good looking fonts</p>
                                    <p class="right-msg">New flat style makes the page more beautiful.</p>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </section>
                <section class="body-section">
                    <div class="section-date">
                        <h5>2019 / 12 / 30 17:03:25</h5>
                    </div>
                    <div class="section-content">
                        <img class="content-image" src="../../../../public/images/5.jpg" alt="" />
                        <div class="content-item">
                            <el-row class="item-row">
                                <el-col class="row-col row-left" :span="4">
                                    <i class="el-icon-search"></i><br />
                                    <span></span>
                                </el-col>
                                <el-col class="row-col row-right" :span="20">
                                    <p class="right-title">Good looking fonts</p>
                                    <p class="right-msg">New flat style makes the page more beautiful.</p>
                                </el-col>
                            </el-row>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "centerNotify",

        data() {
            return {
            }
        }
    }
</script>

<style scoped lang="less">

    @import "../../../assets/css/center";

    #center-notify {
        width: 100%;
        height: 100%;

        & > .notify-inner {
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

                    max-width: 500px;
                    margin: 0 auto 40px auto;
                    padding: 4px;

                    & > .section-date {
                        margin-bottom: 20px;
                        text-align: center;
                        font-size: 1.8rem;
                    }

                    & > .section-content {

                        width: 100%;

                        & > .content-image {
                            background-color: rgba(47, 47, 47, 0.15);
                            width: 100%;
                            min-height: 140px;
                            border-radius: 10px;
                        }

                        & > .content-item {
                            display: flex;
                            width: 100%;
                            height: auto;
                            cursor: pointer;

                            & > .item-row {
                                width: 100%;
                                height: 100%;
                                display: flex;

                                & > .row-col {
                                    height: 100%;
                                }

                                & > .row-left {
                                    margin: auto;
                                    padding: 10px;
                                    text-align: center;
                                    border-right: 1px solid rgba(0, 0, 0, 0.2);

                                    & > i {
                                        font-size: 1.8rem;
                                    }
                                }

                                & > .row-right {
                                    padding: 10px;

                                    & > .right-title {
                                        font-size: 1.6rem;
                                        font-weight: bolder;
                                    }

                                    & > .right-msg {
                                        margin-top: 5px;
                                        font-size: 1.2rem;
                                        word-wrap: break-word;
                                        word-break: break-all;
                                        color: rgba(145, 142, 145, 1);
                                    }
                                }

                            }


                            & > .content-right {
                                vertical-align: top;
                                padding: 0 10px;
                                display: inline-block;
                                width: calc(~"100% - 60px");
                                height: 100%;
                                border-left: 1px solid rgba(220, 220, 220, 0.75);

                                & > .right-title {
                                    font-size: 1.6rem;
                                    font-weight: 600;
                                }

                                & > .right-msg {
                                    margin-top: 5px;
                                    font-size: 1.4rem;
                                    word-wrap: break-word;
                                    word-break: break-all;
                                    color: rgba(195, 195, 195, 0.74);
                                }
                            }
                        }

                    }

                }

            }
        }
    }

</style>