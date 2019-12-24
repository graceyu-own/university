<template>
    <section id="register">
        <back-header></back-header>
        <div class="register-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">注册新的账号</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-nickname">
                            <el-col :span="24"><el-input v-model="form.nicknameInput" placeholder="用户名" clearable /> </el-col>
                        </el-row>
                        <el-row class="content-email">
                            <el-col :span="24">
                                <el-input v-model="form.emailInput" placeholder="邮箱" clearable>
                                    <el-button slot="append" type="primary" :loading="emailBtnLoading" @click="TrySendRegisterMail()">{{emailBtnText}}</el-button>
                                </el-input>
                            </el-col>
                        </el-row>
                        <el-row class="content-emailValid">
                            <el-col :span="24"><el-input v-model="form.emailValidInput" placeholder="邮箱验证码" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="密码" clearable show-password /> </el-col>
                        </el-row>
                        <el-row class="content-password2">
                            <el-col :span="24"><el-input v-model="form.passwordInput2" placeholder="再次输入密码" clearable show-password /> </el-col>
                        </el-row>
                        <!--<el-row>
                            <el-col :span="24"><el-button type="danger" style="width: inherit;" disabled>Touch Verify</el-button></el-col>
                        </el-row>-->
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TryRegister()">注册</el-button></el-col>
                        </el-row>
                        <el-row class="content-action">
                            <el-col :span="24" style="text-align: right;">
                            </el-col>
                        </el-row>
                    </section>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>
    import BackHeader from "@/components/common/haader/BackHeader";
    export default {
        name: "Login",
        components: {BackHeader},
        data(){
            return {

                show: false,
                load: false,

                emailBtnText    : "发送",
                emailBtnLoading : false,
                emailBtnDisabled: false,
                emailBtnCountdown : 10,

                form: {
                    nicknameInput   : "",
                    emailInput      : "",
                    emailValidInput : "",
                    passwordInput   : "",
                    passwordInput2  : "",
                },
            }
        },

        methods: {

            TryRegister: function() {

                this.load = true;

                this.request.AuthRegister(
                    this.form.nicknameInput,
                    this.form.emailInput,
                    this.form.emailValidInput,
                    this.form.passwordInput,
                    this.form.passwordInput2,
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
                            });
                            this.$router.replace("/auth/login");
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
                )

            },

            // try send mail if not within the cooling time
            TrySendRegisterMail: function() {

                this.SetEmailValidButtonStatus(1);

                this.request.SendRegisterMail(
                    this.form.emailInput,

                    r => {

                        // failed
                        if(r.codeType !== 200) {
                            this.SetEmailValidButtonStatus(0);
                            this.$notify.error({
                                message: r.data,
                                duration: 2000
                            })

                        // success
                        } else {
                            this.SetEmailValidButtonStatus(2);
                            this.$notify.success({
                                message: r.data,
                                duration: 2000
                            });
                        }

                    },

                    error => {
                        this.SetEmailValidButtonStatus(0);
                        this.$notify.error({
                            message: error,
                            duration: 2000
                        })
                    }
                )
            },

            SetEmailValidButtonStatus(status) {

                switch (status) {
                    case 0:
                        this.emailBtnText = "发送";
                        this.emailBtnLoading = false;
                        this.emailBtnCountdown = 120;
                        break;
                    case 1:
                        this.emailBtnLoading = true;
                        break;
                    case 2:
                        let t = setInterval(() => {
                            if(this.emailBtnCountdown <= 0) {
                                clearInterval(t);
                                this.SetEmailValidButtonStatus(0);
                                return;
                            }
                            this.emailBtnText = this.emailBtnCountdown;
                            this.emailBtnCountdown--;
                        }, 1000);
                }
            }
        },

        mounted() {
            this.show = true;

        }
    }
</script>

<style scoped lang="less">

    #register {

        display: flex;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.998);

        & > .register-inner {
            display: flex;
            width: 100%;
            margin: auto;

            & > .inner-content {
                width: 100%;
                max-width: 600px;
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