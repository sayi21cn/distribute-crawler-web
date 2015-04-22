$(function() {
	
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
		is_cycle_task = $("#is_cycle_task");
		cycle_task_interval = $("#cycle_task_interval");
		
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
		data["is_cycle_task"] = is_cycle_task.val()
		data["cycle_task_interval"] = cycle_task_interval.val();
		
		$.ajax({
			type : 'post',
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