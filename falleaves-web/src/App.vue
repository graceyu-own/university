<template>
    <div id="app">
        <keep-alive>
            <router-view v-if="$route.meta.keepAlive"></router-view>
        </keep-alive>
        <router-view v-if="!$route.meta.keepAlive"></router-view>
    </div>
</template>

<script>

    export default {
        name: 'app',
        components: {
        },
    }
</script>

<style lang="less">

    @import "assets/css/common/init";
    @import "assets/css/common/effects";

    #app {
        width: 100%;
        height: 100%;
        overflow: hidden;
        background-color: black;

    }
</style>
