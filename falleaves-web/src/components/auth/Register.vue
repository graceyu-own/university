<template>
    <section id="register">
        <div class="register-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><h2 style="text-align: center;">Register</h2></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-nickname">
                            <el-col :span="24"><el-input v-model="auth.nicknameInput" placeholder="Nickname" clearable /> </el-col>
                        </el-row>
                        <el-row class="content-email">
                            <el-col :span="24"><el-input v-model="auth.emailInput" placeholder="Email" clearable>
                                <el-button slot="append" type="primary" :loading="emailBtnLoading" @click="TrySendMail()">{{emailBtnText}}</el-button>
                            </el-input></el-col>
                        </el-row>
                        <el-row class="content-emailValid" v-show="emailValidArea">
                            <el-col :span="24"><el-input v-model="auth.emailValidInput" placeholder="Email Valid" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="auth.passwordInput" placeholder="Password" clearable show-password /> </el-col>
                        </el-row>
                        <el-row class="content-password2">
                            <el-col :span="24"><el-input v-model="auth.passwordInput2" placeholder="Confirm Password" clearable show-password /> </el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24"><el-button type="danger" style="width: inherit;" disabled>Touch Verify</el-button></el-col>
                        </el-row>
                        <el-row class="content-option">
                            <el-col :span="12"><el-link :underline="false" type="primary" @click="ToLogin()">Login</el-link></el-col>
                            <el-col :span="12" style="text-align: right;"><el-link :underline="false" type="primary">Forget password</el-link></el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TryRegister()">Register</el-button></el-col>
                        </el-row>
                    </section>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>
    export default {
        name: "Login",

        data(){
            return {

                show: false,
                load: false,

                emailBtnText    : "Send",
                emailBtnLoading : false,
                emailValidArea  : false,

                auth: {
                    nicknameInput   : "",
                    emailInput      : "",
                    emailValidInput : "",
                    passwordInput   : "",
                    passwordInput2  : "",
                    globalValid     : []
                },
            }
        },

        methods: {

            TryRegister: function() {

                this.load = true;

                let _this = this;
                setTimeout(() => {
                    _this.load = false;

                    _this.$notify.error({
                        message: 'Incorrect credentials',
                        duration: 2000
                    })

                }, 1500);
            },

            // try send mail if not within the cooling time
            TrySendMail: function() {

                this.emailBtnLoading = true;
                this.emailBtnText = "";

            },

            ToLogin: function() {
                this.$router.replace("/auth/login");
            },

            Show: function() {
                this.show = true;
            }
        },

        mounted() {
            this.Show();
        }
    }
</script>

<style scoped lang="less">

    #register {

        width: 100%;
        height: 100%;
        position: absolute; top: 0; left: 0;
        z-index: 11;
        background-image: linear-gradient( 135deg, #43CBFF 10%, #9708CC 100%);
        overflow: hidden;

        & > .register-inner {
            width: 100%;
            position: absolute; bottom: 0; left: 0;
            display: flex;

            & > .inner-content {
                width: 100%;
                max-width: 600px;
                background-color: rgba(255, 255, 255, 1);
                border-radius: 50px 50px 0 0;
                padding: 50px 25px;
                margin: auto;
                box-shadow: 2px 0 10px rgba(47, 47, 47, 0.8);

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

                & /deep/ .el-loading-mask {
                    border-radius: 50px 50px 0 0 !important;
                }
            }
        }
    }
</style>