<template>
    <section id="test">
        <el-row>
            <el-col :span="24">
                <el-col :span="12">123</el-col>
                <el-col :span="12">456</el-col>
            </el-col>

        </el-row>
    </section>
</template>

<script>
    export default {
        name: "Test"
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