<template>
    <section id="center-user">
        <div class="user-inner">
            <div class="inner-header">
                <div class="header-title">
                    <span>User Center</span>
                </div>
            </div>
            <div class="inner-body" ref="innerBody">
                <section class="body-section body-login" v-if="/*isLogin*/true">
                    <el-button type="info" @click="toLogin">Login to unlock more benefits</el-button>
                </section>
                <section class="body-section body-header" v-if="/*isLogin*/true" @click="toUserInfo">
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
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title">Messages</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title-msg">Comments</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-star-off item-icon-left"></i><span class="item-title">Collections</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
                <section class="body-section body-other">
                    <ul class="ul-list">
                        <li class="list-item"><i class="el-icon-postcard item-icon-left"></i><span class="item-title">Help</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-position item-icon-left"></i><span class="item-title">Donation</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                        <li class="list-item"><i class="el-icon-setting item-icon-left"></i><span class="item-title">Settings</span><i class="el-icon-arrow-right item-icon-right"></i></li>
                    </ul>
                </section>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "centerUser",

        data() {
            return {

                isLogin: this.$common.attr.has("token"),
            }
        },

        methods: {

            toLogin() {
                this.$router.push("/auth/login");
            },

            toUserInfo() {
                this.$router.push("/center-user-info");
            }

        },

        mounted() {
        }
    }
</script>

<style scoped lang="less">

    @import "../../../assets/css/center";

    #center-user {
        width: 100%;
        height: 100%;

        & > .user-inner {
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
                    cursor: pointer;

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