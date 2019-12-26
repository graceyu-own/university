<template>
    <section id="login">
        <back-header></back-header>
        <div class="login-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">登录您的账号</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-email">
                            <el-col :span="24"><el-input v-model="form.identifierInput" placeholder="邮箱" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="密码" clearable show-password /> </el-col>
                        </el-row>
                        <el-row v-if="true">
                            <el-col :span="24" style="position: relative">
                                <el-button type="danger" style="width: inherit;" @click="TryBehaviorValid()" v-if="behaviorValidStatus === 1">点击验证</el-button>
                                <el-button type="warning" style="width: inherit;" disabled icon="el-icon-loading" v-if="behaviorValidStatus === 2">验证中</el-button>
                                <el-button type="success" style="width: inherit;" disabled icon="el-icon-check" v-if="behaviorValidStatus === 3">验证成功</el-button>
                            </el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TryLogin()">现在登录</el-button></el-col>
                        </el-row>
                        <el-row class="content-action">
                            <el-col :span="24" style="text-align: right;">
                                <el-link style="margin-right: 10px; font-size: 1.5rem;" :underline="false" type="info" @click="$router.push('/auth/reset-password')" >找回我的账号</el-link>
                                <el-link style="margin-right: 10px; font-size: 1.5rem;" :underline="false" type="info" @click="$router.push('/auth/register')" >立刻注册</el-link>
                            </el-col>
                        </el-row>
                    </section>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>

    import BackHeader from "@/components/common/header/BackHeader";
    export default {

        name: "Login",
        components: {BackHeader},
        data(){
            return {

                show: false,
                load: false,
                behaviorValidStatus: 1,

                form: {
                    identifierInput     : "",
                    passwordInput       : "",
                    behaviorValid       : "",
                },
            }
        },

        methods: {

            TryBehaviorValid: function() {

                this.behaviorValidStatus = 2;

                this.request.AuthBehavior(
                    r => {

                        if(r.codeType !== 200) {

                            this.$notify.error({
                                message: 'Behavior verify failed, please try again',
                                duration: 2000
                            });
                            this.behaviorValidStatus = 1;
                        } else {
                            this.form.behaviorValid = r.data;
                            this.behaviorValidStatus = 3;
                        }
                    },

                    () => {
                        this.behaviorValidStatus = 1;
                    }
                )
            },

            TryLogin: function() {

                this.load = true;

                this.request.AuthLogin(
                    this.form.identifierInput,
                    this.form.passwordInput,
                    this.form.behaviorValid,
                    r => {
                        if(r.codeType !== 200) {
                            this.$notify.error({
                                message: r.data,
                                duration: 2000
                            })
                        } else {
                            this.$notify.success({
                                message: r.data,
                                duration: 2000
                            })
                        }
                    },
                    error => {

                        this.$notify.error({
                            message: error,
                            duration: 2000
                        })
                    },

                    () => {
                        this.load = false;
                    }
                );
            },

            ToForgetPassword: function() {
                this.$router.push("/auth/forgetPassword");
            }
        },

        mounted() {
            this.show = true;
        }
    }
</script>

<style scoped lang="less">

    #login {

        display: flex;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.998);

        & > .login-inner {
            width: 100%;
            margin: auto;
            display: flex;

            & > .inner-content {
                width: 100%;
                max-width: 600px;
                height: 100%;
                padding: 50px 25px;
                margin: auto;

                & > .content-header {
                    margin-bottom: 20px;
                }

                & > .content-body {

                    max-height: calc(70vh - 120px);
                    overflow: auto;

                    & > div{
                        margin-top: 20px;
                    }

                    & > div:first-child {
                        margin-top: 0;
                    }
                }
            }
        }
    }
</style>