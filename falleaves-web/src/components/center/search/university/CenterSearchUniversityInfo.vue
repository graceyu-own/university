<template>
    <section id="center-search-university-info">

        <back-header></back-header>

        <div class="info-inner">
            <el-row class="inner-list">
                <el-col class="list-item list-header" :span="24">
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
                </el-col>
                <el-col class="list-item list-photo" :span="24">
                    <div class="item-title">
                        <span>院校图片</span>
                    </div>
                    <el-carousel class="photo-carousel" trigger="click" height="200px">
                        <el-carousel-item>
                            <img src="../../../../../public/images/5.jpg" alt="">
                        </el-carousel-item>
                        <el-carousel-item>
                            <img src="../../../../../public/images/5.jpg" alt="">
                        </el-carousel-item>
                        <el-carousel-item>
                            <img src="../../../../../public/images/5.jpg" alt="">
                        </el-carousel-item>
                    </el-carousel>
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
                }
                & > .list-photo {
                    padding: 0 20px;
                    & > .photo-carousel {}
                }
            }
        }
    }
</style>