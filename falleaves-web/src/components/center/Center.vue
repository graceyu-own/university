<template>
    <section id="center">
        <div class="center-inner">
            <center-notify v-if="menuIndex === 0"></center-notify>
            <center-search v-if="menuIndex === 1"></center-search>
            <center-user v-if="menuIndex === 2"></center-user>
            <bottom-menu @ToggleMenu="ToggleMenu"></bottom-menu>
        </div>

    </section>
</template>

<script>
    import BottomMenu from "@/components/common/menu/BottomMenu";
    import CenterNotify from "@/components/center/notify/CenterNotify";
    import CenterSearch from "@/components/center/search/CenterSearch";
    import CenterUser from "@/components/center/user/CenterUser";


    export default {
        name: "Center",

        data() {
            return {
                menuIndex: 0
            }
        },

        methods: {

            ToggleMenu(index) {
                this.menuIndex = index;
            }

        },

        components: {
            CenterUser,
            CenterSearch,
            CenterNotify,
            BottomMenu,
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