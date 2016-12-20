<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Echarts</title>
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	</head>
	<style>
		.form-control{width: 150px;margin: 10px auto;}
	</style>
	<body>
		<input type="hidden" value="${tagNum}" />
		<div class="form-group">
			<input class="laydate-icon form-control" type="text" name="laydate" id="laydate"/>
		</div>
		<div class="home-chart" id="room-chart" style="height: 500px; width: 50%;float: left;"></div>
		<div class="home-chart" id="room-chart2" style="height: 500px; width: 50%;float: left;"></div>
	</body>
	<script src="bootstrap/js/jquery.min.js"></script>
	<script src="js/echarts-all.js"></script>
	<script src="laydate/laydate.js"></script>
	<script type="text/javascript">
	drawPie();
// 		获取当天日期
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var year = date.getFullYear();
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = year + seperator1 + month + seperator1 + strDate;
		    return currentdate;
		} 
		
 		$('#laydate').val(getNowFormatDate());
		laydate({
			elem: '#laydate', 
			isclear: false,
			choose:function(data){
				drawPie();
				console.log($('#laydate').val());
			}
		});
		
		
		function drawPie(){
			var myChart = echarts.init(document.getElementById('room-chart'));
			var myChart2 = echarts.init(document.getElementById('room-chart2'));
			$.post('getPieData.do',
					{date:$('#laydate').val(),
					tagNum:"${tagNum}"
					},
					function(data){
						var piedata = [], legenddata = [],bardata = [];
						if(data.length>1){
							for(var i=0;i<data.length;i++){
								piedata[i] = new Object();
								piedata[i].value = (parseFloat(data[i].count)/14.4).toFixed(2);
								piedata[i].name = data[i].roomName;
								legenddata[i] = data[i].roomName;
								bardata[i] = data[i].count;
							}
						}
			var option = {
			    title : {
			        text: '各房间停留分布图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    legend: {
			        orient : 'vertical',
			        x : 'left',
			        data:legenddata
			    },
			    calculable : true,
			    series : [
			        {
			            name:'时间百分比',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', 225],
			            data: piedata
			        }
			    ]
			}
			myChart.setOption(option);
			
			
			var option2 = {
			    tooltip : {
			        trigger: 'item',
			        formatter: "{b}:{c}分钟",
			        axisPointer : {
			            type: 'shadow'
			        }
			    },
			    toolbox: {
			        show : true,
			        orient : 'vertical',
			        y : 'center',
			        feature : {
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            data : legenddata
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            splitArea : {show : true}
			        }
			    ],
			    grid: {
			        x2:40
			    },
			    series : [
			        {
			            name:'时间',
			            type:'bar',
			            data:bardata
			        },
			        
			    ]
			};
			myChart2.setOption(option2);
			
			myChart.connect(myChart2);
			myChart2.connect(myChart);
			
			setTimeout(function (){
			    window.onresize = function () {
			        myChart.resize();
			        myChart2.resize();
			    }
			},200)
			});
		}
	</script>
</html>
