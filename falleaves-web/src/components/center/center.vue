<template>
    <section id="center">
        <div class="center-inner">
            <center-notify v-if="menuIndex === 0"></center-notify>
            <center-search v-if="menuIndex === 1"></center-search>
            <center-user v-if="menuIndex === 2"></center-user>
            <fl-navigation @toggleNav="toggleNav"></fl-navigation>
        </div>

    </section>
</template>

<script>
    import CenterNotify from "@/components/center/notify/center-notify";
    import CenterSearch from "@/components/center/search/center-search";
    import CenterUser from "@/components/center/user/center-user";

    export default {
        name: "Center",

        data() {
            return {
                menuIndex: 0
            }
        },

        methods: {

            toggleNav(index) {
                this.menuIndex = index;
            }

        },

        components: {
            CenterUser,
            CenterSearch,
            CenterNotify,
        }
    }
</script>

<style scoped lang="less">

    #center {
        width: 100%;
        height: 100%;
        background-color: rgba(250, 250, 250, 1);

        & > .center-inner {
            height: 100%;
        }
    }

</style>