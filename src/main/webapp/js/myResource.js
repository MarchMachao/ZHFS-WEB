// 按条件进行查找
$(function() {
	$("#search_btn").click(function() {
		$('#dg').datagrid('load', {
			name : $('#name').val(),
			type : $('#type').combobox("getValue")
		});
	})
})

// 记录的删除和修改操作
$(function() {
	var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of
	// datagrid
	pager.pagination({
		buttons : [ {
			iconCls : 'icon-search',
			handler : function() {
				$('#dg').datagrid('load', {
					name : $('#name').val(),
					type : $('#type').combobox("getValue")
				});
			}
		}, {
			iconCls : 'icon-remove',
			handler : function() {
				var row = $('#dg').datagrid('getSelected');
				if (row) {
					$.messager.confirm("操作提示", "您确定要删除这条记录吗？", function(data) {
						if (data) {
							$.post('deleteResource.do', {
								id : row.id
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
					updateResource(row.id);
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
$('#addResource').form({
	url : 'addResource.do',
	onSubmit : function() {
		var chestr = $('#userlist').combobox('getValues');
		document.getElementById("permitAccountNumber").value = chestr;
		return true;
	},
	success : function(data) {
		var obj = eval("(" + data + ")");
		$.messager.alert('success', obj.content);
		updateTab("资源新增");
		document.getElementById("id").value="";
	}
});

function updateResource(id) {
	$('#myr_tab').tabs('select', "资源新增");
	updateTab("编辑资源");
	$('#addResource').form('load', 'admin/getResourceById.do?id=' + id);

}

//改变tab
function updateTab(tabName) {
	var tab = $('#myr_tab').tabs('getTab', 1); // 取得第2个tab
	$('#myr_tab').tabs('update', {
		tab : tab,
		options : {
			title : tabName
		}
	});
}