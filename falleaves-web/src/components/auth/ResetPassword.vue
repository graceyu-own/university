<template>
    <section id="resetPassword">
        <back-header></back-header>
        <div class="resetPassword-inner">
            <transition name="effect-fromBottomToTop">
                <div class="inner-content" v-loading="load" v-on:click.stop="() => {}" v-show="show">
                    <section class="content-header">
                        <el-row>
                            <el-col><p style="text-align: center; font-size: 2rem;">找回密码</p></el-col>
                        </el-row>
                    </section>
                    <section class="content-body">
                        <el-row class="content-email">
                            <el-col :span="24"><el-input v-model="form.emailInput" placeholder="邮箱" clearable /></el-col>
                        </el-row>
                        <el-row>
                            <el-col :span="24" style="position: relative">
                                <el-button type="danger" style="width: inherit;" @click="TryBehaviorValid()" v-if="behaviorValidStatus === 1">验证</el-button>
                                <el-button type="warning" style="width: inherit;" disabled icon="el-icon-loading" v-if="behaviorValidStatus === 2">验证中</el-button>
                                <el-button type="success" style="width: inherit;" disabled icon="el-icon-check" v-if="behaviorValidStatus === 3">验证成功</el-button>
                            </el-col>
                        </el-row>
                        <el-row class="content-buttons">
                            <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="TryResetPassword()">下一步</el-button></el-col>
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

            TryResetPassword: function() {

                this.load = true;

                this.request.AuthResetPassword(this.form.emailInput,
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

    #resetPassword {

        display: flex;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.998);

        & > .resetPassword-inner {
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