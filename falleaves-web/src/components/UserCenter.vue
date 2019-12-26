<template>
    <section id="user-center">
        <div class="center-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>个人中心</span>
                </div>
            </div>
            <div class="inner-body">
                <section class="body-section body-login" v-if="!isLogin">
                    <el-button type="info" @click="ToLogin()">登录解锁更多权益</el-button>
                </section>
                <section class="body-section body-user" v-if="isLogin">
                    <ul class="section-list">
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-msg">我的信息</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-star-off item-icon-left"></i><span class="item-msg">我的收藏</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
                <section class="body-section body-other">
                    <ul class="section-list">
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-msg">帮助中心</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-position item-icon-left"></i><span class="item-msg">捐赠</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-setting item-icon-left"></i><span class="item-msg">设置</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "UserCenter",

        data() {
            return {

                isLogin: false

            }
        },

        methods: {

            ToLogin: function() {
                this.$router.push("/auth/login");
            },

        },

        mounted() {

            this.$nextTick(() => {
                this.request.AuthCheckLogin(
                    r => {
                        if(r.dataType === 200) {
                            this.isLogin = true;
                        }
                    }
                );
            });


        }
    }
</script>

<style scoped lang="less">

    #user-center {
        width: 100%;
        height: 100%;

        & > .center-inner {
            height: 100%;

            & > .inner-header {
                padding: 30px 20px;

                & > .header-title {
                    font-size: 2.5rem;
                }
            }

            & > .inner-body {
                padding: 0 20px;

                & > .body-section {
                    margin-top: 20px;

                    & > .section-list {
                        font-weight: 500;
                        list-style: none;
                        font-size: 1.6rem;

                        & > .list-item {
                            width: 100%;
                            height: 56px;
                            line-height: 56px;
                            border-bottom: 0.5px solid rgba(0, 0, 0, 0.2);
                            position: relative;

                            & > .item-icon-left {
                                font-size: 2rem;
                                margin-right: 10px;
                                vertical-align: middle;
                            }

                            & > .item-msg {
                                vertical-align: middle;
                            }

                            & > .item-icon-right {
                                position: absolute;
                                top: 50%;
                                margin-top: -7px;
                                right: 0;
                            }
                        }
                    }
                }

                & > .body-login {
                    text-align: center;
                }

            }
        }
    }


</style>