$(function() {
	$("#patternTable").html("");
	$.ajax({
		type : 'GET',
		url : "service?method=template_list",
		dataType: "json", 
		error : function() {
		},
		success : function(data) {
			var show_template_tbody = "<tr><td align=\"center\">模板id</td><td align=\"center\">模板名称</td><td align=\"center\">添加时间</td><td align=\"center\">更新时间</td><td align=\"center\">更新次数</td><td align=\"center\">测试网址</td><td align=\"center\">描述</td><td align=\"center\">操作</td></tr>";
			var template_content_table = "";
			var template_content_tbody = "";
			for(var dataIndex=0;dataIndex<data.length;dataIndex++){
				template_content_table+="<table style=\"visibility: hidden;\" id=\"template_content_"+data[dataIndex].id+"\" align=\"center\" width=\"1200px;\" border=\"1px\" bordercolor=\"#000000\" cellspacing=\"0px\" style=\"border-collapse: collapse\"></table>";
			}
			$("#template_content_show").after(template_content_table);
			
			
			for(var dataIndex=0;dataIndex<data.length;dataIndex++){
				id = data[dataIndex].id;
				template_name = data[dataIndex].template_name;
				template_area = data[dataIndex].template_area;
				template_create_time = data[dataIndex].template_create_time;
				template_update_time = data[dataIndex].template_update_time;
				template_update_number = data[dataIndex].template_update_number;
				template_test_url = data[dataIndex].template_test_url;
				template_describe = data[dataIndex].template_describe;
				
				
				show_template_tbody+="<tr><td align=\"center\">" +id+"</td><td align=\"center\">"+template_name+"</td>"+
				"<td align=\"center\">"+template_create_time+"</td>"+
				"<td align=\"center\">"+template_update_time+"</td>"+
				"<td align=\"center\">"+template_update_number+"</td>"+
				"<td align=\"center\">"+template_test_url+"</td>"+
				"<td align=\"center\">"+template_describe+"</td>"+
				"<td align=\"center\"><a href=\"#\" onclick='show_templates("+id+")'>查看规则</a>&nbsp;删除</td></tr>";
				
				template_content_tbody ="<tr><td align=\"center\">"+template_area+"</td></td>";
				$("#template_content_"+id).html(template_content_tbody);
				template_content_tbody="";
			}
			$("#show_template").html(show_template_tbody);
		}
	});
	
	$("#task_add_button").click(function(){
		data = {};
		task_name = $("#task_name");
		template_id = $("#template_id");
		insert_db_table_name = $("#insert_db_table_name");
		is_use_db_url = $("#is_use_db_url");
		data_category = $("#data_category");
		urls_or_sql = $("#urls_or_sql");
		task_describtion = $("#task_describtion");
		download_thread_num = $("#download_thread_num");
		
		
		if(task_name.val()!=""){
			data["task_name"] = task_name.val();
		}
		if(template_id.val()!=""){
			data["template_id"] = template_id.val();
		}
		if(template_id.val()==""){
			data["template_id"] = 1;
		}
		data["insert_db_table_name"] = insert_db_table_name.val();
		data["is_use_db_url"] = is_use_db_url.val();
		data["data_category"] = data_category.val();
		data["urls_or_sql"] = urls_or_sql.val();
		data["task_describtion"] = task_describtion.val();
		data["download_thread_num"] = download_thread_num.val();
		
		$.ajax({
			type : 'GET',
			url : "service?method=task_add",
			data:data,
			dataType: "json", 
			error : function() {
			},        	
        	success: function (data){
        		$.alert(data);
        	}
       });
	});
	
	 

});