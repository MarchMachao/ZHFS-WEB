<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>智慧服饰</title>
    <meta name="author" content="SIRMly">
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="wrapper">
    <div class="login-container">
        <div class="logo-container">
            <img src="img/logo.png" alt="" id="logo">
            <img src="img/logo-word1.png" alt="" id="logo-word">
        </div>
        <form id="login-form" action="login.do" method="post">
            <div class="input-group">
                <label for="username">账号
                    <i class="iconfont">&#xe601;</i>
                </label>
                <input type="text" id="username" placeholder="请输入账号" value="" name="accountNumber">
            </div>
            <div class="input-group">
                <label for="password">密码
                    <i class="iconfont">&#xe602;</i>
                </label>
                <input type="password" id="password" placeholder="请输入密码" value="" name="userpwd">
            </div>
            <#if callback?has_content>
				<div class="danger-error">账号或密码错误</div>
			</#if>
            <div class="btn-group">
            	<button class="btn-pro" type="button" onclick="window.location.href='register.ftl'" id="register-btn">注&nbsp;册</button>
                <button class="btn-pro" type="submit" id="login-btn" style="margin-left: 40px;">登&nbsp; 录</button>
            </div>
        </form>
        <p id="form-bottom"></p>
    </div>
    <p class="login-bottom">穿戴式老年人行为健康服务平台V1.1</p>
</div>
</body>
</html>