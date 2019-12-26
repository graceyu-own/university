<template>
    <section id="center">
        <div class="center-inner">
            <user-center></user-center>
            <bottom-menu></bottom-menu>
        </div>

    </section>
</template>

<script>
    import BottomMenu from "@/components/common/menu/BottomMenu";
    import UserCenter from "@/components/UserCenter";

    export default {
        name: "Center",

        components: {
            UserCenter,
            BottomMenu,
        }
    }
</script>

<style scoped lang="less">

    #center {
        width: 100%;
        height: 100%;
        background-color: rgba(253, 253, 253, 1);
    }

</style>