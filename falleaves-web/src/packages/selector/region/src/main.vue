<template>
    <section class="fl-selector-region" :ref="'fl-selector-region-' + uniqueId" @click="closeSelector(false, false)">
        <transition name="effect-fromBottomToTop">
            <el-container class="region-container"  v-loading="loading" ref="inner" v-if="show" @click.native.stop="() => {}">
                <el-header class="container-header">
                    <div class="header-title">
                        <p>SELECTOR</p>
                    </div>
                    <div class="header-button">
                        <i class="el-icon-close" @click="closeSelector(false, false)"></i>
                    </div>
                    <div class="header-nav">
                        <el-breadcrumb class="nav-breadcrumb" separator="/">
                            <el-breadcrumb-item class="breadcrumb-item" v-for="(item, index) in dat.nav" v-bind:key="index" @click.native="prev(index, item.id, item.name, item.index)">{{item.name}}</el-breadcrumb-item>
                        </el-breadcrumb>
                    </div>

                </el-header>
                <el-main class="container-body">

                    <div class="body-selector">
                        <div class="selector-title">
                            <span>请选择省份/地区</span>
                        </div>
                        <el-row class="selector-list">
                            <el-col class="list-item" :span="24" v-for="(item, index) in dat.list" v-bind:key="index">
                                <span @click="next(item.id, item.name, index)">{{item.name}}</span>
                            </el-col>
                        </el-row>
                    </div>
                </el-main>
                <el-footer class="container-footer">
                    <el-button type="primary" size="small" @click="closeSelector(true, true)">重置</el-button>
                </el-footer>
            </el-container>
        </transition>
    </section>
</template>

<script>
    export default {
        name: "FlSelectorRegion",

        data() {
            return {

                show: false,
                loading: false,

                dat: {
                    level: 1,
                    nav: [
                    ],
                    list: [

                    ]
                }
            }
        },

        methods: {

            // 关闭选择器
            closeSelector: function(isCallback = false, isReset = false) {
                if(isCallback) {
                    this.callback(isReset, this.dat.nav);
                }

                this.show = false;
                setTimeout(() => {
                    this.parent.removeChild(this.$refs['fl-selector-region-' + this.uniqueId]);
                }, 666);
            },

            // 重设选择器
            resetBody: function() {

                this.$refs['inner-body'].$el.style.height =
                    (this.$refs['inner'].$el.clientHeight -
                        (this.$refs['inner-header'].$el.clientHeight + this.$refs['inner-footer'].$el.clientHeight) - 20) + "px";
                this.$refs['inner-body'].$el.scrollTop = 0;
            },

            // 查询第一级全部省份
            queryAll: function() {
                this.loading = true;
                this.$request.region.QueryAll(
                    r => {
                        this.loading = false;
                        this.dat.list = [];
                        for(let index in r.data) {
                            if(r.data.hasOwnProperty(index)) {
                                this.dat.list.push({
                                    name: r.data[index].name,
                                    id  : r.data[index].id
                                })
                            }
                        }
                    }
                )
            },

            // 通过ID查询后续字节点(省查附属市)
            queryById: function(id) {
                this.loading = true;
                this.$request.region.QueryById(id,
                    r => {
                        if(r.data.length === 0) {
                            this.closeSelector(true, false);
                        } else {
                            this.loading = false;
                            this.dat.list = [];
                            for(let index in r.data) {
                                if(r.data.hasOwnProperty(index)) {
                                    this.dat.list.push({
                                        name: r.data[index].name,
                                        id  : r.data[index].id
                                    })
                                }
                            }
                        }
                    }
                )
            },

            // 下一步
            next: function(id = -1, name = '全国') {

                this.dat.nav.push({
                    id      : id,
                    name    : name,
                });
                setTimeout(() => {
                    //this.resetBody();
                }, 10);

                if (this.dat.level === 1) {
                    this.queryAll();
                } else {
                    this.queryById(id);
                }

                if(this.dat.level++ > this.level && this.level !== -1) {
                    this.closeSelector(true, false);
                }
            },

            // 上一步
            prev: function(arrIndex, id, name) {

                this.dat.level = arrIndex + 1;
                this.dat.nav.splice(arrIndex, 999);
                this.next(id, name);
            }
        },

        mounted() {
            this.next();
            this.show = true;
        }

    }
</script>

<style scoped lang="less">

    .fl-selector-region {
        display: flex;
        flex-direction: column-reverse;
        width: 100%;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        z-index: var(--common-selector-region--main);
        background-color: rgba(45, 45, 45, 0.2);

        @media screen and (max-width: 600px) {
            & > .region-container {
                margin: 0 auto;
                border-radius: 10px 10px 0 0;
            }
        }

        @media screen and (min-width: 600px) {
            & > .region-container {
                margin: auto;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            }
        }

        & > .region-container {
            width: 100%;
            max-width: 600px;
            height: 80%;
            max-height: 500px;
            background-color: rgba(255, 255, 255, 1);
            overflow: hidden;

            & > .container-header {
                width: 100%;
                padding: 20px;
                border-bottom: 0.5px solid rgba(0, 0, 0, 0.3);
                font-size: 1.6rem;
                text-align: center;
                position: relative;
                & > .header-title {

                }
                & > .header-button {
                    position: absolute;
                    right: 20px;
                    top: 20px;
                    font-size: 1.6rem;
                    cursor: pointer;
                    & > i {
                        font-weight: bolder;
                    }
                }

                & > .header-nav {
                    height: 50px;
                    width: calc(~"100% - 30px");
                    position: absolute;
                    top: 60px;
                    left: 0;
                    color: black;
                    padding: 15px 0;
                    margin: 0 15px;
                    overflow-x: auto;
                    overflow-y: hidden;
                    border-bottom: 0.5px solid rgba(0, 0, 0, 0.2);

                    & > .nav-breadcrumb {
                        white-space: nowrap;
                        height: 20px;
                        line-height: 20px;
                        & > span {
                            float: none;
                        }
                    }
                }
            }

            & > .container-body {
                margin-top: 70px;
                padding: 0 20px;
                & > .body-selector {
                    & > .selector-title {
                        margin-bottom: 8px;
                        & > span {
                            font-size: 1.5rem;
                            border-bottom: 2px solid rgba(0, 0, 0, 1);
                            padding-bottom: 2px;
                        }
                    }
                    & > .selector-list {
                        & > .list-item {
                            padding: 4px 0;
                            font-size: 1.35rem;
                            cursor: pointer;
                        }
                    }
                }
            }

            & > .container-footer{
                text-align: right;
                line-height: 60px;
            }
        }
    }

</style>