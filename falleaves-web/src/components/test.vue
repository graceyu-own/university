<template>
    <section id="test">
        <el-card class="box-card" style="margin-top: 80px;">
            <div slot="header">
                <span>卡片名称</span>
                <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
            </div>
            <div class="text item">
                qwe
            </div>
        </el-card>

        <fl-header style="border-bottom: 1px solid rgba(0, 0, 0, 0.2);">
            <span data-float="left">卡片名称</span>
            <span style="font-size: 1.4rem;">qwe</span>
            <span data-float="right">卡片名称</span>
            <span data-float="right">卡片名称</span>
        </fl-header>

    </section>
</template>

<script>
    import FlHeader from "@/packages/header/src/main";
    export default {
        name: "Test",
        components: {FlHeader}
    }
</script>

<style scoped lang="less">
    #test {
        width: 100%;
        height: 600px;
        background-color: white;

        .el-row {
            margin-bottom: 20px;
            &:last-child {
                margin-bottom: 0;
            }
        }
        .el-col {
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .bg-purple-dark {
            background: #99a9bf;
        }
        .bg-purple {
            background: #d3dce6;
        }
        .bg-purple-light {
            background: #e5e9f2;
        }
        .grid-content {
            border-radius: 4px;
            min-height: 36px;
        }
        .row-bg {
            padding: 10px 0;
            background-color: #f9fafc;
        }
    }
</style>