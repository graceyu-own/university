<template>
    <section id="commonHeader">
        <div class="commonHeader-title">
            <span>FALLEAVES</span>
        </div>
    </section>
</template>

<script>
    export default {
        name: "CommonHeader"
    }
</script>

<style scoped lang="less">
    #commonHeader {
        width: 100%;
        height: 50px;
        position: fixed;
        top: 0;
        left: 0;
        border-bottom: 0.43px solid rgba(0, 0, 0, 0.2);
        background-color: rgba(255, 255, 255, 1);

        & > .commonHeader-title {
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