<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    $.ajax({
        url: "${pageContext.request.contextPath}/user/bar",
        dataType: "json",
        success: function (data) {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts表格标题',
                    //
                    subtext: '刘子耀--副标题',
                },
                tooltip: {},
                legend: {
                    data: ['man', 'women']
                },
                xAxis: {
                    data: data.xAxis
                },
                yAxis: {},
                series: [{
                    name: 'man',
                    type: 'bar',
                    data: data.mans
                }, {
                    name: 'women',
                    type: 'bar',
                    data: data.womens
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>