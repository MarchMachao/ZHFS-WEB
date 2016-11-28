//用户模块
$(function() {
	var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	// datagrid
	pager.pagination({
		buttons : [ {
			iconCls : 'icon-search',
			handler : function() {
				$('#dg').datagrid('load', {
					accountNumber : $('#accountNumber').val(),
					nickName : $('#nickName').val(),
				});
			}
		}, {
			iconCls : 'icon-remove',
			handler : function() {
				var row = $('#dg').datagrid('getSelected');
				if (row) {
					$.messager.confirm("操作提示", "您确定要删除这条记录吗？", function(data) {
						if (data) {
							$.get('admin/deleteUser.do', {
								accountNumber : row.accountNumber
							}, function(data) {
								$.messager.alert('提示', data.content, 'info');
								$(".pagination-load").trigger("click");
							})
						} else {

						}
					});
				} else {
					$.messager.alert('提示', "请选中要删除的行", 'info');
				}

			}
		}, {
			iconCls : 'icon-edit',
			handler : function() {
				var row = $('#dg').datagrid('getSelected');
				if (row) {
					updateUser(row.accountNumber);
				} else {
					$.messager.alert('提示', "请选中要修改的行", 'info');
				}
			}
		}, {
			iconCls : 'icon-load',
			handler : function() {
				alert('load');
			}
		} ]
	});
})

// 提交表单
$('#addUser').form({
	url : 'admin/saveUser.do',
	onSubmit : function() {
		return $(this).form('validate');
	},
	success : function(data) {
		var obj = eval("(" + data + ")");
		$.messager.alert('success', obj.content);
	}
});

// 切换tab并加载表单
function updateUser(accountNumber) {
	$('#user_tab').tabs('select', "用户新增");
	updateUserTab("编辑用户");
	$('#accountNumber').textbox({
		editable:false
	});
	$('#addUser').form('load','admin/getUserByAccountNumber.do?accountNumber=' + accountNumber);
	$.get("admin/getUserByAccountNumber.do",{"accountNumber":accountNumber},function(data){
		$("#headicon").attr("src", "http://ocb1neay4.bkt.clouddn.com/"+data.image);
	})
}

// 改变tab
function updateUserTab(tabName) {
	var tab = $('#user_tab').tabs('getTab', 1); // 取得第2个tab
	$('#user_tab').tabs('update', {
		tab : tab,
		options : {
			title : tabName
		}
	});
}

// 查找用户
$(function() {
	$("#search_btn_user").click(function() {
		$('#dg').datagrid('load', {
			accountNumber : $('#accountNumber_search').val(),
			nickName : $('#nickName_search').val()
		});
	})
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
