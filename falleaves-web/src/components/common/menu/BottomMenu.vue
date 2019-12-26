<template>
    <section id="bottom-menu">
        <div class="menu-inner">
            <el-row class="inner-row">
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-view"></i><br />
                        <span>发现</span>
                    </div>
                </el-col>
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-search"></i><br />
                        <span>查找</span>
                    </div>
                </el-col>
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-user"></i><br />
                        <span>我的</span>
                    </div>
                </el-col>
            </el-row>
        </div>
    </section>
</template>

<script>
    export default {
        name: "BottomMenu"
    }
</script>

<style scoped lang="less">

    #bottom-menu {

        width: 100%;
        height: 70px;
        position: fixed;
        bottom: 0;
        left: 0;
        background-color:rgba(255, 255, 255, 0.94);
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);

        & > .menu-inner {

            height: 100%;

            & > .inner-row {

                height: 100%;

                & > .row-col {
                    display: flex;
                    height: 100%;

                    & > div {
                        display: inline-block;
                        margin: auto;
                        text-align: center;

                        & > i {
                            font-size: 2.3rem;
                            font-weight: bolder;
                            margin-bottom: 5px;
                        }
                    }
                }

            }
        }

    }

</style>