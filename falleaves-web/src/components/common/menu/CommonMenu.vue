<template>
    <section id="common-menu" ref="menu">
        <div class="menu-mask"></div>
        <div class="menu-inner">
            <div class="inner-content">
                <section class="content-buttons" ref="innerButtons">
                    <span class="buttons-main el-icon-s-grid" @click="ToggleMenuDisplay()"></span>
                </section>
                <section class="content-header">
                    <div class="header-title">
                        <span>FALLEAVES</span>
                    </div>
                </section>
                <section class="content-body">
                    <el-menu default-active="2-1" class="" text-color="#000" active-text-color="#ffd04b">
                        <el-submenu index="1">
                            <template slot="title">
                                <i class="el-icon-user"></i>
                                <span>User</span>
                            </template>
                            <el-menu-item index="1-1"><i class="el-icon-postcard fix-top"></i>Profile</el-menu-item>
                            <el-menu-item index="1-2"><i class="el-icon-star-off fix-top"></i>Star</el-menu-item>
                            <el-submenu index="1-3">
                                <template slot="title">
                                    <i class="el-icon-setting"></i>
                                    <span>Settings</span>
                                </template>
                                <el-menu-item index="1-3-1"><i class="el-icon-picture-outline-round fix-top"></i>Preference</el-menu-item>
                                <el-menu-item index="1-3-2"><i class="el-icon-star-off fix-top"></i>Account</el-menu-item>
                            </el-submenu>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title">
                                <i class="el-icon-search"></i>
                                <span>Query</span>
                            </template>
                            <el-menu-item index="2-1"><i class="el-icon-postcard fix-top"></i>Universities and Colleges</el-menu-item>
                            <el-menu-item index="2-2"><i class="el-icon-star-off fix-top"></i>Major</el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-warning-outline"></i>
                                <span>About</span>
                            </template>
                            <el-menu-item index="3-1"><i class="el-icon-connection fix-top"></i>Functions</el-menu-item>
                            <el-menu-item index="3-2"><i class="el-icon-chat-round fix-top"></i>Feedback</el-menu-item>
                            <el-menu-item index="3-3"><i class="el-icon-present fix-top"></i>Sponsor</el-menu-item>
                        </el-submenu>
                    </el-menu>
                </section>
                <section class="content-footer">
                    <div class="footer-copyright">
                        <span>Copyright.ALL 2019 Falleaves</span>
                    </div>
                </section>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CommonMenu",

        data() {
            return {
                menuStatus: true,
            }
        },

        methods: {

            ToggleMenuDisplay: function() {

                this.menuStatus = !this.menuStatus;

                if(!this.menuStatus){
                    this.$refs.menu.style.transform = "translateX("+ (this.$refs.menu.clientWidth) +"px)";
                    this.$refs.innerButtons.style.transform = "translateX(-50px)";
                } else {
                    this.$refs.menu.style.transform = "translateX(0)";
                    this.$refs.innerButtons.style.transform = "translateX(0)";
                }
            },
        },

        mounted() {
            this.$refs.menu.style.left = - this.$refs.menu.clientWidth + "px";
        }
    }
</script>

<style scoped lang="less">

    #common-menu {

        width: 100%;
        max-width: 400px;
        height: 100%;
        position: absolute; top: 0;
        z-index: 50;
        transform: translateZ(0);

        transition: transform 666ms;

        & > .menu-inner {

            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 1);

            & > .inner-content {
                position: relative;
                height: 100%;
                padding: 10px;

                & > .content-buttons {

                    width: 30px;
                    position: absolute; top: 10px; right: -40px;
                    cursor: pointer;
                    transform: translateZ(0);

                    transition: transform 666ms;

                    & > .buttons-main {

                        display: inline-block;
                        width: inherit;
                        height: 30px;
                        color: #000000;
                        font-size: 2.5rem;
                        text-align: center;
                        line-height: 30px;
                        outline: none;
                    }
                }

                & > .content-header {
                    height: 40px;
                    line-height: 30px;
                    text-align: center;
                    border-bottom: 0.3px solid rgba(0, 0, 0, 0.3);
                    padding-bottom: 10px;

                    & > .header-title {
                        font-size: 1.5rem;
                    }
                }

                & > .content-body {
                    margin: 10px 0;
                    height: calc(100% - 90px);
                    overflow-y: auto;
                }

                & > .content-footer {

                    height: 40px;
                    padding: 10px 0;
                    border-top: 0.3px solid rgba(0, 0, 0, 0.3);
                    line-height: 20px;
                    text-align: center;

                    & > .footer-copyright {
                        font-size: 1.2rem;
                    }

                }
            }
        }
    }
</style>