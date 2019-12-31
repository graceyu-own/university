<template>
    <section id="backHeader">
        <div class="backHeader-backButton">
            <span class="el-icon-arrow-left" @click="ToBack()"><span style="font-weight: normal">Back</span></span>
        </div>
        <div class="backHeader-title">
            <span>FALLEAVES</span>
        </div>
    </section>
</template>

<script>
    export default {
        name: "BackHeader",

        methods: {

            ToBack: function() {
                this.$router.go(-1);
            }
        }
    }
</script>

<style scoped lang="less">

    #backHeader {
        width: 100%;
        height: 50px;
        position: fixed;
        top: 0;
        left: 0;
        border-bottom: 0.43px solid rgba(0, 0, 0, 0.2);
        background-color: rgba(255, 255, 255, 1);

        & > .backHeader-backButton {
            height: 100%;
            display: flex;
            position: absolute;

            & > span {
                margin: auto;
                font-size: 1.5rem;
                padding: 0 15px;
                color: #3a8ee6;
                font-weight: bolder;
                cursor: pointer;
            }
        }

        & > .backHeader-title {
            height: 100%;
            display: flex;

            & > span {
                font-size: 1.6rem;
                font-weight: bold;
                letter-spacing: 1px;
                margin: auto;
            }
        }
    }
</style>