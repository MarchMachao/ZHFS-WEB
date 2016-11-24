$(function() {
	$('#search_btn').click(function() {
		$('#dg').datagrid('load', {
			name : $('#name').val(),
			describe : $('#describe').val()
		});
	})

	$('#password_btn').click(function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.post("getPwdById.do", {
				id : row.id
			}, function(data) {
				$("#fm").form("load", {
					content : data.content
				});
			}, "JSON");
			$('#dlg').dialog('open');
		}else{
			$.messager.alert('提示', "请选中查看的资源", 'info');
		}
	})
})