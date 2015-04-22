<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="index.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>模板列表</title>
</head>
<body>
	<br />
	<br />
	<table align="center" width="1200px;">
		<tr>
			<td align="center"><a href="index.jsp">模板列表</a></td>
			<td align="center"><a href="cycle_task_list.jsp">循环任务列表</a></td>
			<td align="center"><a href="task_list.jsp">任务列表</a></td>
			<td align="center"><a href="cycle_task_add.jsp">循环任务添加</a></td>
			<td align="center"><a href="task_add.jsp">一次任务添加</a></td>
			<td align="center"><a href="pattern_config.jsp">增加模板</a></td>
		</tr>
	</table>
	<br />

	<table id="show_template" align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse">
		<tr>
			<td align="center">模板id</td>
			<td align="center">模板名称</td>
			<td align="center">抽取内容</td>
			<td align="center">添加时间</td>
			<td align="center">更新时间</td>
			<td align="center">更新册数</td>
			<td align="center">测试网址</td>
			<td align="center">描述</td>
		</tr>
	</table>
	<br />
	<br />
	<table id="template_content_show" align="center" width="1200px;"
		border="1px" bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse"></table>
</body>
</html>

<script type="text/javascript">
	var show_templates = function(id) {
		pattern = $("#template_content_show").html();
		if (pattern.length > 0) {
			$("#template_content_show").html("");
			return;
		}
		$("#template_content_show").html($("#template_content_" + id).html());
	};
</script>
