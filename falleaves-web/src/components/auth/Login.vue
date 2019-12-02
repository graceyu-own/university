<template>
    <section id="login">
        <div class="login-inner">
            <div class="inner-content" v-loading="load">
                <el-row>
                    <el-col><h4 style="text-align: center;">Log In</h4></el-col>
                </el-row>
                <el-row class="content-email">
                    <el-col :span="24"><el-input v-model="email" placeholder="Email" clearable /> </el-col>
                </el-row>
                <el-row class="content-password">
                    <el-col :span="24"><el-input v-model="password" placeholder="Password" clearable show-password /> </el-col>
                </el-row>
                <el-row class="content-validCode" v-if="false">
                    <el-col :span="24">
                        <el-input v-model="validCode" placeholder="Valid code" clearable>
                            <el-button slot="append">Send</el-button>
                        </el-input>
                    </el-col>
                </el-row>
                <el-row class="content-option">
                    <el-col :span="12"><el-checkbox v-model="rememberPassword">Remember password</el-checkbox></el-col>
                    <el-col :span="12" style="text-align: right;"><el-link :underline="false" type="primary">Forget password</el-link></el-col>
                </el-row>
                <el-row class="content-buttons">
                    <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="tryLogin()" class="common-font">Log In</el-button></el-col>
                </el-row>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "Login",

        data(){
            return {

                load: false,

                email: "",
                password: "",
                validCode: "",

                rememberPassword: false
            }
        },

        methods: {

            tryLogin: function() {

                this.load = true;

                let _this = this;
                setTimeout(() => {
                    _this.load = false;
                }, 1500);

            }
        }
    }
</script>

<style scoped lang="less">

    #login {

        width: 100%;
        height: 100%;
        position: absolute; top: 0; left: 0;
        z-index: 11;
        background-color: rgba(255, 255, 255, 0);

        & > .login-inner {
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

                & > div{
                    margin-top: 20px;
                }

                & > div:first-child {
                    margin-top: 0;
                }

                & /deep/ .el-loading-mask {
                    border-radius: 50px 50px 0 0 !important;
                }
            }
        }
    }
</style>