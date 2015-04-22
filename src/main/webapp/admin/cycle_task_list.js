$(function() {

	$.ajax({
		type : 'GET',
		url : "service?method=task_list",
		dataType: "json", 
		error : function() {
		},
		success : function(data) {
			
			var format_status = function(status_num){
				if(status_num=='0'){
					return "等待";
				}
				if(status_num=='100'){
					return "完成";
				}
				if(status_num != '0'&& status_num != '100'){
					return "完成"+status_num+"%";
				}
			};
			var format_data_category = function(data_category_id){
				if(data_category_id == 1){
					return "html";
				}
				if(data_category_id == 2){
					return "json";
				}
				return "error!";
			};
			var format_is_use_db = function(is_use){
				if(is_use == 1){
					return "是";
				}
				if(is_use == 2){
					return "否";
				}
				return "error!";
			};
 			
			var tbody = "<tr><td align=\"center\">任务ID</td><td align=\"center\">任务名称</td><td align=\"center\">模板ID</td><td align=\"center\">数据库表名</td><td align=\"center\">是否使用数据库中URL</td><td align=\"center\">数据类型</td><td align=\"center\" style=\"width:10px\">URLS或SQL</td><td align=\"center\">创建时间</td><td align=\"center\">更新时间</td><td align=\"center\">下载线程数</td><td align=\"center\">状态</td><td align=\"center\">描述</td></tr>";
			status = "";
			for(var dataIndex=0;dataIndex<data.length;dataIndex++){
				
				tbody+="<tr>"+
					"<td align=\"center\">" +data[dataIndex].id+"</td>"+
					"<td align=\"center\">" +data[dataIndex].task_name+"</td>"+
					"<td align=\"center\">"+data[dataIndex].template_id+"</td>"+
					"<td align='center'>"+data[dataIndex].insert_db_table_name+"</td>"+
					"<td align='center'>"+format_is_use_db(data[dataIndex].is_use_db_url)+"</td>"+
					"<td align='center'>"+format_data_category(data[dataIndex].data_category)+"</td>"+
					"<td align='center'>"+"严重影响美观！"+"</td>"+
					"<td align='center'>"+data[dataIndex].task_create_date+"</td>"+
					"<td align='center'>"+data[dataIndex].task_update_time+"</td>"+
					"<td align='center'>"+data[dataIndex].download_thread_num+"</td>"+
					"<td align='center'>"+format_status(data[dataIndex].task_status)+"</td>"+
					"<td align='center'>"+data[dataIndex].task_describtion+"</td>"+
					"</tr>";
			//	"<td align='center'>"+data[dataIndex].urls_or_sql+"</td>"+
				status = "";
			}
			
			$("#task_list_table").html(tbody);
		}
	});

	 

});