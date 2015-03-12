<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>规则模板配置</title>
</head>
<body>
	<br />
	<br />
	<table align="center" width="1200px;">
		<tr>
			<td align="center"><a href="index.jsp">任务添加</a></td>
			<td align="center"><a href="task_list.jsp">任务列表</a></td>
			<td align="center"><a href="pattern_config.jsp">增加模板</a></td>
		</tr>
	</table>
	<br />
	<table align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse">
		<tr>
			<td>模板名称</td>
			<td><input type="text" id="template_name" placeholder="模板名称"
				value="电影天堂_最新电影抽取模板" size="50" /></td>
			<td></td>
		</tr>
		<tr>
			<td>网页抽取模板</td>
			<td><textarea id="template_area" rows="10" cols="100"
					placeholder="CssPath实体类对应的json表示形式">{"htmlPathList":[{"pathName":"dytt_movie_title","dirPathList":[".co_content8 > ul","table"],"dirIndexList":[0,0],"pathList":["tbody","tr","td","b","a"],"pathIndexList":[0,1,1,0,0]},{"pathName":"dytt_movie_detail_url","dirPathList":[".co_content8 > ul","table"],"dirIndexList":[0,0],"pathList":["tbody","tr","td","b","a"],"pathIndexList":[0,1,1,0,0],"attrName":"href"}]}</textarea>
			<td><input type="button" value="测试" onclick="template_test();" /></td>
		</tr>
		<tr>
			<td>测试网址</td>
			<td><input type="text" id="template_test_url" size="99"
				placeholder="请输入测试网址"
				value="http://www.ygdy8.net/html/gndy/dyzz/list_23_1.html" /></td>
			<td></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><textarea rows="7" cols="100" id="template_describe"
					placeholder="备注">抽取目录页的电影标题和详情页链接</textarea>
			<td></td>
		</tr>
		<tr>
			<td width="200"></td>
			<td><input type="button" value="添加" onclick="template_commit();" /></td>
			<td></td>
		</tr>
	</table>
	<br />

	<table align="center" width="1200px;" border="1px"
		bordercolor="#000000" cellspacing="0px"
		style="border-collapse: collapse">
		<tr>
			<td>测试结果</td>
			<td id="test_result"></td>
		</tr>
	</table>
</body>
</html>

<script type="text/javascript">
	var template_commit = function() {
		var template_name = $("#template_name").val();
		var template_area = $("#template_area").text();
		var template_test_url = $("#template_test_url").val();
		var template_describe = $("#template_describe").text();

		data = {};
		data["template_name"] = template_name;
		data["template_area"] = template_area;
		data["template_test_url"] = template_test_url;
		data["template_describe"] = template_describe;
		
		$.ajax({
			type : 'GET',
			url : "service?method=template_save",
			data : data,
			contentType:'utf-8',
			error : function() {
				$("#test_result").html("error fail!");
			},
			success : function(data) {
				$("#test_result").html(data);
			}
		});
	};

	var template_test = function() {
		var template_area = $("#template_area");
		var templateTestUrl = $("#template_test_url");
		var template_content = template_area.text();
		var template_test_url = templateTestUrl.val()
		if (template_content.length < 5) {
			alert("模板不正确，请修改");
			templateArea.text("");
			templateArea.focus();
			return;
		}
		if (template_test_url.length < 5) {
			alert("测试网址不正确，请修改");
			templateTestUrl.val("");
			templateTestUrl.focus();
			return;
		}

		data = {};
		data["template_content"] = template_content;
		data["template_test_url"] = template_test_url;

		$.ajax({
			type : 'GET',
			url : "service?method=template_test",
			data : data,
			error : function() {
				$("#test_result").html("error fail!");
			},
			success : function(data) {
				$("#test_result").html(data);
			}
		});

	};
</script>
