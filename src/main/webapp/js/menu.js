//改变tab
function updateTab(tabName) {
	var tab = $('#menu_tab').tabs('getTab', 1); // 取得第2个tab
	$('#menu_tab').tabs('update', {
		tab : tab,
		options : {
			title : tabName
		}
	});
}

// 添加一个标签页
function updateMenu(menuId) {

	$('#menu_tab').tabs('select', "菜单新增");
	updateTab("菜单更新");
	$('#ff').form('load', 'admin/getMenuById.do?menuId=' + menuId);

}

// 提交表单,添加菜单,并对数据合法性进行检测
$('#ff').form({
	url : 'admin/saveMenu.do',
	onSubmit : function() {
		return $(this).form('validate');
	},
	success : function(data) {
		$('#menu_tab').tabs('select', "菜单列表");
		$('#dg').datagrid('reload');// 刷新表格
		$.messager.alert('提示', eval("("+data+")").content, 'info');
		$("#ff").form("clear");// 清空表单
		updateTab("菜单新增");
	}
});

// 按条件进行查找
$(function() {
	$("#search_btn").click(function() {
		$('#dg').datagrid('load', {
			menuName : $('#menuName').val(),
			permiton : $('#permition').val()
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
							$.get('admin/deleteMenu.do', {
								menuId : row.menuId
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
					updateMenu(row.menuId);
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

// 页面加载完毕后,给下拉框指定默认值
$(document).ready(function() {
	$("#parentId").val("0");
});
