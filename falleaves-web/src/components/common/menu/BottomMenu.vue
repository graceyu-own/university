<template>
    <section id="bottom-menu">
        <div class="menu-inner">
            <el-row class="inner-row">
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-view"></i><br />
                        <span>Notice</span>
                    </div>
                </el-col>
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-search"></i><br />
                        <span>Search</span>
                    </div>
                </el-col>
                <el-col class="row-col" :span="8">
                    <div>
                        <i class="el-icon-user"></i><br />
                        <span>User</span>
                    </div>
                </el-col>
            </el-row>
        </div>
    </section>
</template>

<script>
    export default {
        name: "BottomMenu",

        mounted() {

            document.querySelectorAll(".menu-inner > .inner-row > .row-col > div").forEach((value, index) => {
                value.addEventListener("click", () => {

                    this.$emit('ToggleMenu', index)
                })
            })

        }

    }
</script>

<style scoped lang="less">

    #bottom-menu {

        width: 100%;
        height: 70px;
        position: fixed;
        bottom: 0;
        left: 0;
        z-index: var(--common--menu--bottom-menu);
        background-color:rgba(255, 255, 255, 0.94);
        box-shadow: 0 0 10px rgba(47, 47, 47, 0.1);

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
                        cursor: pointer;

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