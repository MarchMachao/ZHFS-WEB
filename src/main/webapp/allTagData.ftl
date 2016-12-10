<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>智慧服饰</title>
		<link rel="stylesheet" href="css/allTagData.css "/>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
	</head>
	<body class="wrapper" style="padding:20px 20px;">
		<#list tagLocationData as item>
			<div class="person_div">
				<div class="person_info">
					<strong>NO.</strong><span>${item.tagNum}</span><br />
					<strong>姓名:</strong>${item.name}<br />
					<strong>性别:</strong>${item.sex}<br />
					<strong>年龄:</strong>${item.age}<br />
					<strong>科室:</strong>老年科<br />
				</div>
				<img class="head_pic" src="img/pic.jpg" />
				<div class="other_info">
					<strong>当前位置:</strong>${item.roomName}<br /><br />
					<strong>身体状况:</strong>急性上呼吸道感染 慢性阻塞性肺疾病 慢性心源性心脏病<br />
				</div>
			</div>
		</#list>
	</body>
</html>
