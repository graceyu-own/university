<template>
    <section id="info">

        <fl-header-default title="FALLEAVES"></fl-header-default>

        <div class="info-inner">
            <p>{{msg}}</p>
        </div>
    </section>
</template>

<script>

    export default {
        name: "Info",

        data(){
            return {

                msg: "Unknown Message",
            }
        },

        mounted() {
            this.msg = this.$route.params.msg;
        }
    }
</script>

<style scoped lang="less">

    #info {
        display: flex;
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: rgba(255, 255, 255, 0.998);

        & > .info-inner {
            margin: auto;
        }
    }

</style>