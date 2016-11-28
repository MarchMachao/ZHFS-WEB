//用户自己信息维护

//表单加载
$('#userform').form('load','getCurrentUser.do');

//头像加载
$.get("getCurrentUser.do",function(data){
	$("#headicon").attr("src", "http://ocb1neay4.bkt.clouddn.com/"+data.image);
})

//input file 事件,预览上传文件
$("#image").change(function() {
	var fileUrl = getObjectURL(this.files[0]);
	if (fileUrl) {
		$("#headicon").attr("src", fileUrl);
	}
});

// 建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

//提交表单
$('#userform').form({
	url : 'updateUser.do',
	onSubmit : function() {
		return $(this).form('validate');
	},
	success : function(data) {
		var obj = eval("(" + data + ")");
		$.messager.alert('success', obj.content);
	}
});
