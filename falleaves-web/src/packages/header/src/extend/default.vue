<template>
    <fl-header class="fl-header" :split="true">
        <span slot="left" class="header-back el-icon-arrow-left" v-routers:back><span>{{backText}}</span></span>
        <span slot="center" class="header-title">{{title}}</span>
    </fl-header>
</template>

<script>
    export default {
        name: "FlHeaderDefault",

        props: {
            backText: {
                type: String,
                default: ""
            },
            title: {
                type: String,
                default: "FALLEAVES"
            }
        }
    }
</script>

<style scoped lang="less">
    .fl-header {
        background-color: rgba(255, 255, 255, 1);
        & .header-back {
            color: black;
            font-size: 1.8rem;
            font-weight: bolder;
            cursor: pointer;
            & > span {
                font-weight: normal;
            }
        }
        & .header-title {
            font-size: 1.5rem;
            padding: 0 15px;
            color: #3a8ee6;
            font-weight: bolder;
            cursor: pointer;
        }
    }
</style>