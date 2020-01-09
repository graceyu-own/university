<template>
    <section class="auth">
        <back-header></back-header>
        <div class="auth-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">Retrieve Password</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-email">
                            <el-col :span="24"><el-input v-model="form.emailInput" placeholder="Email" clearable /></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24" style="position: relative">
                                <el-button type="danger" style="width: inherit;" @click="TryBehaviorValid()" v-if="behaviorValidStatus === 1">Touch Verify</el-button>
                                <el-button type="warning" style="width: inherit;" disabled icon="el-icon-loading" v-if="behaviorValidStatus === 2">Verifying...</el-button>
                                <el-button type="success" style="width: inherit;" disabled icon="el-icon-check" v-if="behaviorValidStatus === 3">Verify Successful</el-button>
                            </el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TryResetPassword()">Next</el-button></el-col>
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
        name: "ResetPassword",

        components: {BackHeader},

        data(){
            return {

                show: false,
                load: false,
                behaviorValidStatus: 1,

                form: {
                    emailInput     : "",
                    behaviorValid  : "",
                },
            }
        },

        methods: {

            TryBehavior: function() {

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

            TryResetPassword: function() {

                this.load = true;

                this.$request.auth.resetPassword(this.form.emailInput,
                    r => {
                        if(r.codeType !== 200){
                            this.$notify.error({
                                message: r.data,
                                duration: 2000
                            });
                        } else {
                            this.$router.replace("/info/" + r.data);
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