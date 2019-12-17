<template>
    <div id="imageValid" ref="imageValid">
        <div class="imageValid-header">

        </div>
    </div>
</template>

<script>
    export default {
        name: "image-valid",

        props: {

        },

        data(){
            return {
                el: null,

                width: 0,
                posLeft: 0,
                posBottom: 0,
                title: "Touch Verify",
                backgroundColor: "rgba(255, 80, 80, 0.98)",

            }
        },

        methods: {



        },

        mounted() {

            /*this.$refs.imageValid.style.width = this.el.offsetWidth;
            this.$refs.imageValid.style.borderRadius = this.el.style.borderRadius;
            this.$refs.imageValid.style.backgroundColor = this.el.style.backgroundColor;*/

        }

    }
</script>

<style scoped lang="less">

    #imageValid {
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0; left: 0;
        background-color: rgba(45, 45, 45, 0.4);
        z-index: 999;

        & > .imageValid-content {

            width: 80%;
            height: 200px;
            position: absolute;
            background-color: rgba(255, 80, 80, 0.98);
            padding: 10px;

            & > .content-header {
                height: 30px;
                color: white;
                font-size: 1.4rem;
                text-align: center;
            }


        }

    }
</style>