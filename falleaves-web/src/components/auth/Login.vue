<template>
    <section id="login">
        <div class="login-content">
            <el-row>
                <el-col><h4 style="text-align: center;">Log in</h4></el-col>
            </el-row>
            <svg class="icon login-loading auto-rotation" aria-hidden="true" ref="indexLoading" v-if="load">
                <use xlink:href="#retUI-loading"></use>
            </svg>
            <el-row class="content-email">
                <el-col :span="24"><el-input v-model="email" placeholder="邮箱" clearable /> </el-col>
            </el-row>
            <el-row class="content-password">
                <el-col :span="24"><el-input v-model="password" placeholder="密码" clearable show-password /> </el-col>
            </el-row>
            <el-row class="content-validCode" v-if="false">
                <el-col :span="24">
                    <el-input v-model="validCode" placeholder="验证码" clearable>
                        <el-button slot="append">发送</el-button>
                    </el-input>
                </el-col>
            </el-row>
            <el-row class="content-option">
                <el-col :span="12"><el-checkbox v-model="rememberPassword"> 记住密码</el-checkbox></el-col>
                <el-col :span="12" style="text-align: right;"><el-link :underline="false" type="primary">忘记密码</el-link></el-col>
            </el-row>
            <el-row class="content-buttons">
                <el-col :span="24"><el-button type="primary" style="width: 100%;" @click="tryLogin()">登录</el-button></el-col>
            </el-row>
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

                if(this.email === ''){
                    this.$message({
                        showClose: true,
                        message: '邮箱不能为空',
                        duration: 2000,
                        type: 'error',
                    })
                }
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

        & > .login-content {
            width: 100%;
            position: absolute; bottom: 0; left: 0;
            background-color: rgba(255, 255, 255, 1);
            border-radius: 50px 50px 0 0;
            padding: 5px 25px 30px 25px;

            & > .login-loading {
                position: absolute; top: 25px; right: 25px;
                font-size: 1.3rem;
            }

            & > div{
                margin-top: 20px;
            }
        }

    }

</style>