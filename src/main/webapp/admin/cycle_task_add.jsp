<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="cycle_task_add.js"></script>
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

	<table id="taskAdd" align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse">
		<tr>
			<td colspan="2">循环任务添加</td>
		</tr>
		<tr>
			<td width="300">任务名称:</td>
			<td><input type="text" id="task_name" placeholder="任务名称" value="测试任务"/><br /></td>
		</tr>
		<tr>
			<td>抽取模板ID:</td>
			<td><input type="text" id="template_id" placeholder="模板ID，默认为1" value="1"/></td>
		</tr>
		<tr>
			<td>下载线程数:</td>
			<td><input type="text" id="download_thread_num" placeholder="下载线程数" value="1"/></td>
		</tr>
		<tr>
			<td>循环任务间隔时间:</td>
			<td><input type="text" id="cycle_task_interval" placeholder="非循环任务无效" value="3600000"/>ms</td>
		</tr>
		<tr>
			<td>数据存储表名:</td>
			<td><input type="text" id="insert_db_table_name" placeholder="使用的数据库表" value="dytt_movie_dir"/></td>
		</tr>
		<tr>
			<td>是否使用数据库中URL:</td>
			<td><select id="is_use_db_url" name="是否使用数据库URL">
					<option value="1">是</option>
					<option value="2" selected="selected">否</option>
			</select>
		</tr>
		<tr>
			<td>数据类型:</td>
			<td><select id="data_category" name="data_category">
					<option value="1" selected="selected">html</option>
					<option value="2">json</option>
			</select>
		</tr>
		<tr>
			<td>数据库查询URL的SQL语句或以","分隔的URL列表</td>
			<td><textarea id="urls_or_sql" rows="10" cols="100" placeholder='数据库查询URL的SQL语句或以","分隔的URL列表'>http://www.ygdy8.net/html/gndy/dyzz/list_23_1.html,http://www.ygdy8.net/html/gndy/dyzz/list_23_2.html</textarea></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><textarea id="task_describtion" rows="5" cols="100" placeholder='任务描述'>这里填写任务描述，简介任务内容</textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><input id="task_add_button" type="button" value="提循环任务添加" /></td>
		</tr>
	</table>
	<br />
	<br />
	<br />


	<table id="template_content_show" align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
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
