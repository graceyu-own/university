<template>
    <section id="login" class="auth">
        <back-header></back-header>
        <div class="auth-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">Log in to your account</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-email">
                            <el-col :span="24"><el-input v-model="form.identifier" placeholder="Nickname / Email" clearable /></el-col>
                        </el-row>
                        <el-row class="content-password">
                            <el-col :span="24"><el-input v-model="form.passwordInput" placeholder="Password" clearable show-password /> </el-col>
                        </el-row>
                        <el-row v-if="true">
                            <el-col :span="24">
                                <el-button type="danger" style="width: inherit;" @click="tryBehavior()" v-if="behaviorValidStatus === 1">Touch Verify</el-button>
                                <el-button type="warning" style="width: inherit;" disabled icon="el-icon-loading" v-if="behaviorValidStatus === 2">Verifying...</el-button>
                                <el-button type="success" style="width: inherit;" disabled icon="el-icon-check" v-if="behaviorValidStatus === 3">Verify Successful</el-button>
                            </el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="tryLogin()">Login</el-button></el-col>
                        </el-row>
                        <el-row class="content-action">
                            <el-col :span="24" style="text-align: right;">
                                <el-link style="margin-right: 10px; font-size: 1.5rem;" :underline="false" type="info" @click="$router.push('/auth/reset-password')" >Forget Password</el-link>
                                <el-link style="margin-right: 10px; font-size: 1.5rem;" :underline="false" type="info" @click="$router.push('/auth/register')" >Register</el-link>
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
                    identifier    : "",
                    credential    : "",
                    behaviorValid : "",
                },
            }
        },

        methods: {

            tryBehavior: function() {

                this.behaviorValidStatus = 2;

                this.$request.auth.behavior(
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

            tryLogin: function() {

                this.load = true;

                this.$request.auth.login(
                    this.form.identifier,
                    this.form.credential,
                    this.form.behaviorValid,

                    // success
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
                            this.attr.set("token", true);
                        }
                    },

                    // error
                    error => {

                        this.$notify.error({
                            message: error,
                            duration: 2000
                        })
                    },

                    // finally
                    () => {
                        this.load = false;
                    }
                );
            },
        },

        mounted() {
            this.show = true;
        }
    }
</script>

<style scoped lang="less">

    @import "../../assets/css/auth";

</style>