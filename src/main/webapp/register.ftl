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
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
<div class="wrapper">
    <div class="login-container">
        <div class="logo-container">
            <img src="img/logo.png" alt="" id="logo">
            <img src="img/logo-word1.png" alt="" id="logo-word">
        </div>
        <form id="login-form" action="register.do" method="post">
            <div class="input-group">
                <label for="username">账号
                    <i class="iconfont">&#xe601;</i>
                </label>
                <input type="text" id="username" placeholder="请输入账号"  name="accountNumber">
            </div>
            <div class="input-group">
                <label for="nickname">昵称
                    <i class="iconfont">&#xe601;</i>
                </label>
                <input type="text" id="nickname" placeholder="请输入昵称"  name="nickname">
            </div>
            <div class="input-group">
                <label for="email">邮箱
                    <i class="iconfont">&#xe601;</i>
                </label>
                <input type="text" id="email" placeholder="请输入邮箱"  name="email">
            </div>
            <div class="input-group">
                <label for="password">密码
                    <i class="iconfont">&#xe602;</i>
                </label>
                <input type="password" id="password" placeholder="请输入密码"  name="password">
            </div>
            <div class="input-group">
                <label for="repwd">密码确认
                    <i class="iconfont">&#xe602;</i>
                </label>
                <input type="password" id="repwd" placeholder="请确认密码"  name="repwd">
            </div>
            <div class="btn-group">
            	<button class="btn-pro" type="button" onclick="window.location.href='Login.ftl'" id="register-btn" style="margin-left: 40px;">取&nbsp;消</button>
                <button class="btn-pro" type="submit" id="login-btn">注 册</button>
            </div>
            <div class="danger-error">${requestScope.callback}</div>
        </form>
        <p id="form-bottom"></p>
    </div>
    <p class="login-bottom">穿戴式老年人行为健康服务平台 V1.1</p>
</div>
</body>
</html>