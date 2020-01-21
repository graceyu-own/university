<template>
    <section class="fl-header">
        <div class="fl-header__inner"
            :style="{backgroundColor: bgColor, borderBottom: split ? '0.43px solid rgba(0, 0, 0, 0.2)' : 'none'}">
            <div class="fl-header__inner-left">
                <slot name="left"></slot>
            </div>
            <div class="fl-header__inner-center">
                <slot name="center"></slot>
            </div>
            <div class="fl-header__inner-right">
                <slot name="right"></slot>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "FlHeader",

        props: {
            split: {
                type: Boolean,
                default: false
            },
            bgColor: {
                type: String,
                default: 'rgba(0, 0, 0, 0)'
            }
        },
    }
</script>

<style scoped lang="less">

    .fl-header {

        @height: 60px;

        width: 100%;
        height: @height;
        position: fixed;
        top: 0;
        left: 0;
        z-index: var(--common-header--main);
        & > .fl-header__inner {
            text-align: center;
            width: 100%;
            height: @height;
            font-size: 1.6rem;
            display: flex;
            justify-content: space-between;
            color: white;
            & > div {
                margin: auto 0;
            }
            & > .fl-header__inner-left {
                position: relative;
                line-height: @height;
                z-index: var(--common-header--inner-left);
                & > * {
                    margin-left: 15px;
                }
            }
            & > .fl-header__inner-center {
                position: absolute;
                width: 100%;
                top: 0;
                left: 0;
                text-align: center;
                line-height: @height;
                z-index: var(--common-header--inner-center);
                & > * {
                    margin: 0 15px;
                }
            }
            & > .fl-header__inner-right {
                position: relative;
                line-height: @height;
                z-index: var(--common-header--inner-right);
                & > * {
                    margin-right: 15px;
                }
            }
        }
    }
</style>