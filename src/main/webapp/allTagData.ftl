<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>智慧服饰</title>
		<link rel="stylesheet" href="css/allTagData.css "/>
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
		<script src="bootstrap/js/jquery.min.js"></script>
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
				<img class="head_pic" src="http://ohyi4k153.bkt.clouddn.com/${item.image}" />
				<div class="other_info">
					<strong>当前位置:</strong>${item.roomName}<br /><br />
					<strong>身体状况:</strong>${item.health}<br />
				</div>
			</div>
		</#list>
	</body>
	<script>
		$('.person_div').on("click",function(){
			var tagNum=$(this).find("span").html();
			window.location.href='jumpToOneLocation.do?tagNum='+tagNum;
		})
	</script>
</html>
