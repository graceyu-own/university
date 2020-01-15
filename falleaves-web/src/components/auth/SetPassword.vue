<template>
    <section class="auth">
        <common-header></common-header>
        <div class="auth-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">Set your password</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="New Password" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password2">
                            <el-col :span="24"><el-input v-model="form.passwordInput2" placeholder="Confirm New Password" clearable /></el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="trySetPassword()">Submit</el-button></el-col>
                        </el-row>
                    </section>
                </div>
            </transition>
        </div>
    </section>
</template>

<script>
    import CommonHeader from "@/components/common/header/CommonHeader";

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
            trySetPassword: function () {

                this.load = true;

                this.$request.auth.setPassword(
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
    @import "../../assets/css/auth";
</style>