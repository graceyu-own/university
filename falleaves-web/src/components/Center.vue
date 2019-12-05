<template>
    <section id="center">
        <CommonMenu></CommonMenu>
    </section>
</template>

<script>

    import CommonMenu from '@/components/common/CommonMenu'

    export default {
        name: "Center",

        components: {
            CommonMenu
        }
    }
</script>

<style scoped lang="less">

    #center {
        width: 100%;
        height: 100%;
        background-color: rgba(52, 52, 52, 0.1);
    }

</style>