<template>
    <section class="auth">
        <back-header></back-header>
        <div class="auth-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">Sign up for a new account</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-nickname">
                            <el-col :span="24"><el-input v-model="form.nicknameInput" placeholder="Nickname" clearable /> </el-col>
                        </el-row>
                        <el-row class="content-email">
                            <el-col :span="24">
                                <el-input v-model="form.emailInput" placeholder="Email" clearable>
                                    <el-button slot="append" type="primary" :loading="emailBtnLoading" @click="trySendRegisterMail()">{{emailBtnText}}</el-button>
                                </el-input>
                            </el-col>
                        </el-row>
                        <el-row class="content-emailValid">
                            <el-col :span="24"><el-input v-model="form.emailValidInput" placeholder="Email Code" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="Password" clearable show-password /> </el-col>
                        </el-row>
                        <el-row class="content-password2">
                            <el-col :span="24"><el-input v-model="form.passwordInput2" placeholder="Confirm Password" clearable show-password /> </el-col>
                        </el-row>
                        <!--<el-row>
                            <el-col :span="24"><el-button type="danger" style="width: inherit;" disabled>Touch Verify</el-button></el-col>
                        </el-row>-->
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="tryRegister()">Register</el-button></el-col>
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
    import BackHeader from "@/components/common/header/BackHeader";
    export default {
        name: "Login",
        components: {BackHeader},
        data(){
            return {

                show: false,
                load: false,

                emailBtnText    : "Send",
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

            tryRegister: function() {

                this.load = true;

                this.$request.auth.register(
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
            trySendRegisterMail: function() {

                this.setEmailValidButtonStatus(1);

                this.$request.auth.sendRegisterMail(
                    this.form.emailInput,

                    r => {

                        // failed
                        if(r.codeType !== 200) {
                            this.setEmailValidButtonStatus(0);
                            this.$notify.error({
                                message: r.data,
                                duration: 2000
                            })

                        // success
                        } else {
                            this.setEmailValidButtonStatus(2);
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

            setEmailValidButtonStatus(status) {

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
                            if (this.emailBtnCountdown <= 0) {
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
    @import "../../assets/css/auth";
</style>