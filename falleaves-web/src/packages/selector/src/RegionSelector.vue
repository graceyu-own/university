<template>
    <section id="common-region-selector" ref="common-region-selector">
        <div class="selector-inner"  v-loading="loading">
            <div class="inner-header">
                <div class="header-title">
                    <p>Selector</p>
                </div>
                <div class="header-close">
                    <i class="el-icon-close" @click="CloseSelector"></i>
                </div>
                <div class="header-nav">
                    <el-breadcrumb class="nav-list" separator="/">
                        <el-breadcrumb-item class="list-item" v-for="(item, index) in dat.nav" v-bind:key="index" @click="Prev(item.id, item.index)">{{item.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
            </div>
            <div class="inner-body">
                <div class="body-selector">
                    <div class="selector-title">
                        <span>请选择省份/地区</span>
                    </div>
                    <el-row class="selector-list">
                        <el-col class="list-item" :span="24" v-for="(item, index) in dat.list" v-bind:key="index">
                            <span @click="Next(item.id, item.name, index)">{{item.name}}</span>
                        </el-col>
                    </el-row>

                </div>
            </div>
            <div class="inner-footer">

            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "RegionSelector",

        data() {
            return {

                loading: false,

                dat: {
                    index: 1,
                    nav: [

                    ],
                    list: [

                    ]
                }
            }
        },

        methods: {

            CloseSelector: function() {
                document.body.removeChild(this.$refs['common-region-selector']);
            },

            QueryAll: function() {
                this.loading = true;
                this.$request.region.QueryAll(
                    r => {
                        this.loading = false;
                        this.dat.list = [];
                        for(let index in r.data.data) {
                            this.dat.list.push({
                                name: r.data.data[index].name,
                                id  : r.data.data[index].id
                            })
                        }

                        this.dat.index++;
                    }
                )
            },

            QueryById: function(id, name, index) {
                this.loading = true;
                this.$request.region.QueryById(id,
                    r => {
                        this.loading = false;
                        this.dat.nav.push({
                            id      : id,
                            name    : name,
                            index   : index
                        });
                        this.dat.list = [];
                        for(let index in r.data.data) {
                            this.dat.list.push({
                                name: r.data.data[index].name,
                                id  : r.data.data[index].id
                            })
                        }

                        this.dat.index++;
                    }
                )
            },

            Next: function(id, name, index) {

                if (this.dat.index === 1) {
                    this.QueryAll();
                } else {
                    this.QueryById(id, name, index);
                }

                if(this.dat.index > this.level) {

                    this.callback(this.dat.nav);
                    this.CloseSelector();
                }

            },

            Prev: function(id, index) {

            }
        },

        mounted() {

            let t = this.$common.timings.init();
            t.startTimings();

            this.Next(-1, "", -1);

            t.stopTimings();



        }
    }
</script>

<style scoped lang="less">

    #common-region-selector {
        width: 100%;
        height: 100%;
        background-color: rgba(45, 45, 45, 0.2);
        position: fixed;
        top: 0;
        left: 0;
        z-index: 1999;

        & > .selector-inner {
            width: 100%;
            height: 80%;
            max-height: 500px;
            background-color: rgba(255, 255, 255, 1);
            border-radius: 10px 10px 0 0;
            position: absolute;
            bottom: 0;
            left: 0;
            overflow-y: auto;

            & > .inner-header {
                padding: 20px;
                position: relative;
                border-bottom: 0.5px solid rgba(0, 0, 0, 0.3);
                & > .header-title {
                    text-align: center;
                    font-size: 1.6rem;
                }
                & > .header-close {
                    position: absolute;
                    right: 20px;
                    top: 20px;
                    font-size: 1.6rem;
                }
                & > .header-nav {
                    padding: 30px 10px 10px 10px;
                    & > .nav-list {
                        & > .list-item {
                            cursor: pointer;
                        }
                    }

                }
            }

            & > .inner-body {
                height: calc(~"100% - 120px");
                padding: 20px;
                overflow-y: auto;

                & > .body-selector {
                    & > .selector-title {
                        font-size: 1.3rem;
                        color: rgba(45, 45, 45, 0.7);
                        margin-bottom: 10px;
                    }
                    & > .selector-list {
                        & > .list-item {
                            font-size: 1.4rem;
                            margin-bottom: 10px;
                            & > span {
                                cursor: pointer;
                            }
                        }
                    }
                }
            }
        }
    }

</style>