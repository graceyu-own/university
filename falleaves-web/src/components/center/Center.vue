<template>
    <section id="center">
        <div class="center-inner">
            <find-center v-show="menuIndex === 0"></find-center>
            <search-center v-show="menuIndex === 1"></search-center>
            <user-center v-show="menuIndex === 2"></user-center>
            <bottom-menu @ToggleMenu="ToggleMenu"></bottom-menu>
        </div>

    </section>
</template>

<script>
    import BottomMenu from "@/components/common/menu/BottomMenu";
    import UserCenter from "@/components/center/UserCenter";
    import FindCenter from "@/components/center/NotifyCenter";
    import SearchCenter from "@/components/center/SearchCenter";

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
            SearchCenter,
            FindCenter,
            UserCenter,
            BottomMenu,
        }
    }
</script>

<style scoped lang="less">

    #center {
        width: 100%;
        height: 100%;
        background-color: rgba(243, 243, 243, 1);

        & > .center-inner {
            height: 100%;
        }
    }

</style>