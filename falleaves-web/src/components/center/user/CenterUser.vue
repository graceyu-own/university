<template>
    <section id="user-center">
        <div class="center-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>User Center</span>
                </div>
            </div>
            <div class="inner-body" ref="innerBody">
                <section class="body-section body-login" v-if="/*isLogin*/true">
                    <el-button type="info" @click="ToLogin">Login to unlock more benefits</el-button>
                </section>
                <section class="body-section body-header" v-if="/*isLogin*/true">
                    <div class="header-content">
                        <p class="content-nickname">Grace Yu</p>
                        <p class="content-description">This is a description...</p>
                    </div>
                    <div class="header-photo">
                        <el-avatar :fit="'fill'" src="/images/5.jpg" :size="70"></el-avatar>
                    </div>
                </section>
                <section class="body-section body-user" v-if="/*isLogin*/true">
                    <ul class="ul-list">
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-msg">Messages</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-msg">Comments</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-star-off item-icon-left"></i><span class="item-msg">Collections</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
                <section class="body-section body-other">
                    <ul class="ul-list">
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-msg">Help</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-position item-icon-left"></i><span class="item-msg">Donation</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-setting item-icon-left"></i><span class="item-msg">Settings</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CenterUser",

        data() {
            return {

                isLogin: this.$common.attr.has("token"),
            }
        },

        methods: {

            ToLogin() {
                this.$router.push("/auth/login");
            },

        },

        mounted() {
        }
    }
</script>

<style scoped lang="less">

    @import "../../../assets/css/center";

    #user-center {
        width: 100%;
        height: 100%;

        & > .center-inner {
            height: 100%;
            overflow-y: auto;

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
                    margin-top: 20px;
                }

                & > .body-header {
                    min-height: 70px;
                    position: relative;

                    & > .header-content {
                        display: inline-block;
                        width: calc(~"100% - 70px");
                        vertical-align: top;
                        padding: 0 20px 0 0;

                        & > .content-nickname {
                            font-size: 2rem;
                            font-weight: 500;
                        }

                        & > .content-description {
                            word-break: break-all;
                            word-wrap: break-word;
                            margin-top: 10px;
                            font-size: 1.4rem;
                            color: rgba(150, 150, 150, 0.8)
                        }
                    }

                    & > .header-photo {
                        display: inline-block;
                    }
                }

                & > .body-login {
                    text-align: center;
                }

            }
        }
    }


</style>