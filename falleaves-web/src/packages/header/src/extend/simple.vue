<template>
    <fl-header class="fl-header">
        <span slot="left" class="header-back el-icon-arrow-left" v-routers:back></span>
    </fl-header>
</template>

<script>
    export default {
        name: "FlHeaderSimple"
    }
</script>

<style scoped lang="less">
    .fl-header {
        & .header-back {
            font-size: 1.8rem;
            font-weight: bolder;
            color: black;
        }
    }
</style>