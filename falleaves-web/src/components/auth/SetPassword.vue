<template>
    <section id="setPassword">
        <common-header></common-header>
        <div class="setPassword-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">设置您的密码</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="新的密码" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password2">
                            <el-col :span="24"><el-input v-model="form.passwordInput2" placeholder="再次输入密码" clearable /></el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TrySetPassword()">完成</el-button></el-col>
                        </el-row>
                    </section>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>
    import CommonHeader from "@/components/common/haader/CommonHeader";

    export default {
        name: "SetPassword",

        components: {CommonHeader},

        data(){
            return {

                show: false,
                load: false,

                form: {
                    passwordInput     : "",
                    passwordInput2    : "",
                },
            }
        },

        methods: {
            TrySetPassword: function () {

                this.load = true;

                this.request.AuthSetPassword(
                    "data",
                    this.form.passwordInput,
                    this.form.passwordInput2,
                    r => {
                        if(r.codeType !== 200){
                            this.$notify.error({
                                message: r.data,
                                duration: 2000
                            });
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
                        });
                    },

                    () => {
                        this.load = false;
                    }
                )
            }
        },

        mounted() {



            this.show = true;
        }
    }
</script>

<style scoped lang="less">

    #setPassword {

        display: flex;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.998);

        & > .setPassword-inner {
            display: flex;
            width: 100%;
            margin: auto;

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

                    & > div {
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