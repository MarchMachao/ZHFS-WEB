$.extend($.fn.validatebox.defaults.rules, {
	isNumber : {
		validator : function(value, param) {

			return /^-?\d+\.?\d*$/.test(value);

		},
		message : '请输入正确的数字!'
	},
	istelNumber : {
		validator : function(value, param) {

			return /^0?1[3|4|5|8|7|9][0-9]\d{8}$/.test(value);

		},
		message : '请输入正确的电话号码!'
	},
	isAccountNumber : {
		validator : function(value,param){
			return /[A-Za-z0-9]{1,15}$/.test(value);
		},
		message : '用户名不合法，不可以包含特殊字符，!'
	
	},
	isPassword : {
		validator : function(value,param){
			return /[A-Za-z0-9!.]{6,40}$/.test(value);
		},
		message : '密码不合法，格式为字母和数字的组合,长度在6~40位'
	},
	iseMail : {
		validator : function(value,param){
			return /[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/.test(value);
		},
		message : '请输入正确的邮箱!'
	}
});

$.ajaxSetup({  
    complete:function(XMLHttpRequest,textStatus){  
          if(textStatus=="parsererror"){  
               $.messager.alert('提示信息', "登陆超时！请重新登陆！", 'info',function(){  
                   window.location.href = '/login.do';  
               });  
          } else if(textStatus=="error"){  
              $.messager.alert('提示信息', "请求超时！请稍后再试！", 'info');  
          }  
    }  
}); 