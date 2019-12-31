<template>
    <section id="index" ref="index">
        <div class="index-content">
            <div class="content-logo">
                <el-avatar class="logo-image" icon="el-icon-picture-outline"></el-avatar>
            </div>
            <div class="content-msg">
                <p>Meet infinite possibilities</p>
            </div>
        </div>
        <div class="index-loading">
            <svg class="icon content-loading auto-rotation" aria-hidden="true" ref="loading">
                <use xlink:href="#retUI-loading"></use>
            </svg>
        </div>
    </section>
</template>

<script>

    export default {
        name: "Index",


        data() {
            return {

            }
        },

        methods: {

            Init: function() {

                    setTimeout(() => {
                        this.$router.replace("/center");
                    }, 5000)

            }
        },

        mounted() {

            this.Init();
        },
    }
</script>

<style scoped lang="less">

    #index {
        display: flex;
        width: 100%;
        height: 100%;
        position: relative;
        background-color: rgba(255, 255, 255, 1);

        & > .index-content {
            width: 100%;
            margin: auto;
            color: black;
            text-align: center;

            & > .content-logo {
                margin-bottom: 30px;

                & > .logo-image {
                    background-color: rgba(255, 255, 255, 0);
                    color: black;
                    font-size: 4rem;
                }
            }

            & > .content-msg {
                font-size: 2.2rem;
                margin-bottom: 20px;
            }

        }

        & > .index-loading {
            width: 100%;
            position: absolute;
            bottom: 40px;
            font-size: 3.5rem;
            text-align: center;

        }
    }
</style>