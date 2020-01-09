import Vue from 'vue';
import echarts from 'echarts';
import 'echarts/lib/chart/line';

const dat = {

    2015: {
        min     : 678,
        avg     : 692,
        batch   : 548
    },
    2016: {
        min     : 684,
        avg     : 687,
        batch   : 537
    },
    2017: {
        min     : 689,
        avg     : 677,
        batch   : 537
    },
    2018: {
        min     : 688,
        avg     : 695,
        batch   : 547
    },
    2019: {
        min     : 688,
        avg     : 695,
        batch   : 547
    },
};

function UniversityScoreChart(element, data) {

    this.chart = echarts.init(element);
    this.chart.clear();

    let year = [];
    let min  = [];
    let avg  = [];
    let batch = [];

    for (let datKey in dat) {
        year.push(datKey);
        min.push(dat[datKey].min);
        avg.push(dat[datKey].avg);
        batch.push(dat[datKey].batch);
    }

    let t = min.concat(avg).concat(batch);
    t.sort((a, b) => {
        return a - b;
    });

    let option = {
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: year
        },
        yAxis: {
            type: 'value',
            min: t[0] - 10,
            max: t[t.length - 1] + 10,
            splitNumber: 10
        },
        series: [
            {
                name: '最低分',
                data: min,
                type: 'line',
                smooth: true
            },
            {
                name: '平均分',
                data: avg,
                type: 'line',
                smooth: true
            },
            {
                name: '批次分',
                data: batch,
                type: 'line',
                smooth: true
            },
        ]
    };

    this.chart.setOption(option);
}

function registry() {
    Vue.prototype.$charts = {
        universityScore: UniversityScoreChart
    }
}

export default registry