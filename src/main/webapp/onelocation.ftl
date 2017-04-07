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
			<div class="home-chart" id="room-chart" style="height: 350px; width: 50%;float: left;"></div>
			<div class="home-chart" id="room-chart2" style="height: 350px; width: 50%;float: left;"></div>
		</div>
		<div id="btn1" style="margin: 10px auto; text-align: center;">
			<button id="button1" type="button" class="btn btn-info" onclick="">显示详情</button>
		</div>
		<div id="btn2" style="margin: 10px auto; text-align: center; display: none;">
			<button id="button2" type="button" class="btn btn-info" onclick="">隐藏详情</button>
			<button id="button3" type="button" class="btn btn-info" onclick="">切换视图</button>
		</div>
		<div style="width:100%;">
			<div id="room-chart-3" class="home-chart" style="height:300px; width: 100%; display:none;"></div>
			<div id="tb" style="padding: 0 13%; display:none;">
				<table id="trailtb" class="table table-hover">
					<thead>
						<tr>
							<th>房间号</th>
							<th>房间名</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>持续时间</th>
						</tr>
					<thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		drawPie();
		//获取当天日期，用于laydate
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
				drawTrail();
			}
		});
		
		
		$("#button1").click(function(){
			$("#btn1").css("display","none");
			$("#btn2").css("display","block");
			$("#room-chart-3").css("display","block");
			$("#tb").css("display","none");
			drawTrail();
		});
		
		$("#button2").click(function(){
			$("#btn1").css("display","block");
			$("#btn2").css("display","none");
			$("#room-chart-3").css("display","none");
			$("#tb").css("display","none");
		});
		
		var switchnum=true;
		$("#button3").click(function(){
			if(switchnum == true){
				console.log("switchtrue");
				switchnum = false;
				$("#room-chart-3").css("display","none");
				$("#tb").css("display","block");
			}else{
				console.log("switchfalse");
				switchnum = true;
				$("#tb").css("display","none");
				$("#room-chart-3").css("display","block");
			}
		});
		
		//初始化echarts的DOM
		var myChart = echarts.init(document.getElementById('room-chart'));
		var myChart2 = echarts.init(document.getElementById('room-chart2'));
		var myChart3 = echarts.init(document.getElementById('room-chart-3'));
		
		//画饼图和柱状图
		function drawPie(){
			myChart = echarts.init(document.getElementById('room-chart'));
			myChart2 = echarts.init(document.getElementById('room-chart2'));
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
			    tooltip: {
			        trigger: 'item'
			    },
			    grid: {
			        borderWidth:0,
			        y: 80,
			        y2: 60
			    },
			    calculable: true,
			   
			    xAxis: [
			        {
			            type: 'category',
			            show: true,
			            data: legenddata,
			            splitLine:false,
			        }
			    ],
			   
			    yAxis: [
			        {
			            type: 'value',
			            show: false
			        }
			    ],
			    series: [
			        {
			            name: '时间',
			            type: 'bar',
			            itemStyle: {
			                normal: {
			                    color: function(params) {
			                        // build a color map as your need.
			                        var colorList = [
											'#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
											'#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
											'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
											];
			                        return colorList[params.dataIndex]
			                    },
			                    label: {
			                        show: true,
			                        position: 'top',
			                        formatter: '{b}\n{c}分钟'
			                    }
			                }
			            },
			            data: bardata,
			            
			        }
			    ]
			};
			myChart2.setOption(option2);
			
			myChart.connect(myChart2);
			myChart2.connect(myChart);
			
			});
		}
		
		//画行为轨迹图
		function drawTrail(){
			
			myChart3 = echarts.init(document.getElementById('room-chart-3'));
			$.get('getTrailByDateAndTagnum.do',
					{
					date:$('#laydate').val(),
					tagNum:"${tagNum}"
					},
					function(data){
// 						var tbroomNum=[],tbstart=[],tbend=[],tbsub=[];
						var roomName=[],chart3data=[],chart3colorlib=[];
						
						for(var i=0;i<data.length;i++){
							roomName[i]=data[i].roomName;
							
							if(data[i].sub==null){
								chart3data[i]=0;
							}else{
								chart3data[i]=data[i].sub;
							}
							
							chart3colorlib[i]=data[i].color;
							
						}
						
						//绘制表格
						$("#trailtb tbody").empty();
						
						for(var i in data){
							if(data[i].end=="未离开"){
								var newTr = '<tr>' + '<td>'
										+ data[i].roomNum
										+ '</td>' + '<td>'
										+ data[i].roomName
										+ '</td>' + '<td>'
										+ data[i].start
										+ '</td>' + '<td>'
										+ data[i].end
										+ '</td>' + '<td>'
										+ "未离开"
										+ '</td>' + '</tr>';
							}
							else{
						
								var newTr = '<tr>' + '<td>'
										+ data[i].roomNum
										+ '</td>' + '<td>'
										+ data[i].roomName
										+ '</td>' + '<td>'
										+ data[i].start
										+ '</td>' + '<td>'
										+ data[i].end
										+ '</td>' + '<td>'
										+ parseInt((data[i].sub)/60)+"分"+(data[i].sub)%60+"秒"
										+ '</td>' + '</tr>';
							}
							$("#trailtb tbody").append(newTr);
						}
						
						//绘制echarts图表
						var option3 = {
						    title : {
						        text: '行为轨迹图',
						        x:'center',
						        y:'top'
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
						    calculable : false,
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
		
		//监听浏览器的resize事件，调整echarts图表的大小
		setTimeout(function (){
		    window.onresize = function () {
		    	myChart.resize();
			    myChart2.resize();
		        myChart3.resize();
		    }
		},200)
	</script>
</html>
