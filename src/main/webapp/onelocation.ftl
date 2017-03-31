<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Echarts</title>
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		<script src="bootstrap/js/jquery.min.js"></script>
		<script src="js/echarts-all.js"></script>
		<script src="laydate/laydate.js"></script>
	</head>
	<style>
		body{padding: 5px 20px;}
		.form-control{width: 150px;margin: 10px auto;}
		button{width: 150px;}
	</style>
	<body>
		<input type="hidden" value="${tagNum}" />
		<div class="form-group">
			<input class="laydate-icon form-control" type="text" name="laydate" id="laydate"/>
		</div>
		<div class="clearfix">
			<div class="home-chart" id="room-chart" style="height: 300px; width: 50%;float: left;"></div>
			<div class="home-chart" id="room-chart2" style="height: 300px; width: 50%;float: left;"></div>
		</div>
		<div style="margin: 10px auto; text-align: center;">
			<button type="button" class="btn btn-info" onclick="drawTrail();">显示详情</button>
		</div>
		<div class="home-chart" id="room-chart-3" style="height:300px; width: 100%;"></div>
		<div style="padding: 0 13%;">
			<table class="table table-hover">
				<tr>
					<th>房间号</th>
					<th>房间名</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>持续时间</th>
				</tr>
				<tr>
					<tbody>
						
					</tbody>
				</tr>
			</table>
		</div>
	</body>
	
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
					{
						date:$('#laydate').val(),
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
		
		function drawTrail(){
			var myChart3 = echarts.init(document.getElementById('room-chart-3'));
			
			
			$.get('getTrailByDateAndTagnum.do',
					{
					date:$('#laydate').val(),
					tagNum:"${tagNum}"
					},
					function(data){
						var tbroomNum=[],tbstart=[],tbend=[],tbsub=[];
						var roomName=[],chart3data=[],chart3colorlib=[];
						for(var i=0;i<data.length;i++){
							roomName[i]=data[i].roomName;
							chart3data[i]=data[i].sub;
							chart3colorlib[i]=data[i].color;
							
							tbroomNum[i]=data[i].rooNum;
							tbstart[i]=data[i].start;
							tbend[i]=data[i].end;
							tbsub[i]=parseInt(data[i].sub/60)+"分"+(data[i].sub%60)+"秒"
						}
			var option3 = {
					    title : {
					        text: '行为轨迹图',
					        x:'center',
					        y:'bottom'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{b}:{c}秒",
					        axisPointer : {
					            type: 'shadow'
					        }
					    },
					    dataZoom: {
	                        show: true,
	                        start : 0,
	                        height: 15
	                    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            data : roomName
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'时间',
					            type:'bar',
					            data: chart3data,
					          	itemStyle: {
								    normal: {
								        color: function(params) {
								            var colorList = chart3colorlib;
								            return colorList[params.dataIndex]
								        },
								        label: {
								            show: false,
								            position: 'top',
								            formatter: '{b}\n{c}'
								        }
								    }
								},
					        },
					        
					    ]
					};
			
			myChart3.setOption(option3);
					});
		}
	</script>
</html>
