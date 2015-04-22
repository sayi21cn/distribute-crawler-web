<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="task_list.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>规则模板配置</title>
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
	<br/>
	
	<table id="task_list_table" align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse">
		<tr>
			<td align="center">任务ID</td>
			<td align="center">任务名称</td>
			<td align="center">模板ID</td>
			<td align="center">数据库表名</td>
			<td align="center">是否使用数据库中URL</td>
			<td align="center">数据类型</td>
			<td align="center">URLS或SQL</td>
			<td align="center">状态</td>
			<td align="center">创建时间</td>
			<td align="center">更新时间</td>
			<td align="center">描述</td>
		</tr>

	</table>
</body>
</html>
