<template>
    <section id="common-region-selector" ref="common-region-selector">
        <el-row class="selector-inner"  v-loading="loading" ref="inner">
            <el-col class="inner-header" :span="24" ref="inner-header">
                <div class="header-title">
                    <p>地区选择器</p>
                </div>
                <div class="header-close">
                    <i class="el-icon-close" @click="closeSelector(false, false)"></i>
                </div>
                <div class="header-nav">
                    <el-breadcrumb class="nav-list" separator="/">
                        <el-breadcrumb-item class="list-item" v-for="(item, index) in dat.nav" v-bind:key="index" @click.native="prev(index, item.id, item.name, item.index)">{{item.name}}</el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
            </el-col>
            <el-col class="inner-body" :span="24" ref="inner-body">
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
            </el-col>
            <el-col class="inner-footer" :span="24" ref="inner-footer">
                <el-col class="footer-reset">
                    <el-button type="primary" size="small" @click="closeSelector(true, true)">重置</el-button>
                </el-col>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    export default {
        name: "RegionSelector",

        data() {
            return {

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

            closeSelector: function(isCallback = false, isReset = false) {
                if(isCallback) {
                    this.callback(isReset, this.dat.nav);
                }
                document.body.removeChild(this.$refs['common-region-selector']);
            },

            resetBody: function() {

                this.$refs['inner-body'].$el.style.height =
                    (this.$refs['inner'].$el.clientHeight -
                    (this.$refs['inner-header'].$el.clientHeight + this.$refs['inner-footer'].$el.clientHeight) - 20) + "px";
                this.$refs['inner-body'].$el.scrollTop = 0;
            },

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

            next: function(id = -1, name = '全国') {

                this.dat.nav.push({
                    id      : id,
                    name    : name,
                });
                setTimeout(() => {
                    this.resetBody();
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

            prev: function(arrIndex, id, name) {

                this.dat.level = arrIndex + 1;
                this.dat.nav.splice(arrIndex, 999);
                this.next(id, name);
            }
        },

        mounted() {

            let t = this.$common.timings.Init();
            t.startTimings();

            this.next();

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
        z-index: var(--common--selector--region-selector);

        & > .selector-inner {
            width: 100%;
            height: 80%;
            max-height: 500px;
            background-color: rgba(255, 255, 255, 1);
            border-radius: 10px 10px 0 0;
            position: absolute;
            bottom: 0;
            left: 0;
            overflow-y: hidden;

            & > .inner-header {
                padding: 20px;
                border-bottom: 0.5px solid rgba(0, 0, 0, 0.3);
                font-size: 1.6rem;
                text-align: center;
                & > .header-close {
                    position: absolute;
                    right: 20px;
                    top: 20px;
                    font-size: 1.6rem;
                    cursor: pointer;
                }
                & > .header-nav {
                    padding: 30px 10px 10px 10px;
                    & > .nav-list {
                        & > .list-item {
                            cursor: pointer;
                            margin-bottom: 10px;
                        }
                    }

                }
            }

            & > .inner-body {
                height: calc(~"100% - 180px");
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

            & > .inner-footer{
                width: 100%;
                height: 40px;
                & > .footer-reset {
                    padding: 10px 20px;
                    text-align: right;
                }
            }
        }
    }

</style>