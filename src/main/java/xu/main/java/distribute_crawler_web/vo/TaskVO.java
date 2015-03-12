package xu.main.java.distribute_crawler_web.vo;

public class TaskVO {
	private int id;
	private String task_name;
	private String template_id;
	private String insert_db_table_name;
	private String is_use_db_url;
	private String data_category;
	private String urls_or_sql;
	private String task_status;
	private String task_describtion;
	private String task_create_date;
	private String task_update_time;
	private String download_thread_num;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getInsert_db_table_name() {
		return insert_db_table_name;
	}

	public void setInsert_db_table_name(String insert_db_table_name) {
		this.insert_db_table_name = insert_db_table_name;
	}

	public String getIs_use_db_url() {
		return is_use_db_url;
	}

	public void setIs_use_db_url(String is_use_db_url) {
		this.is_use_db_url = is_use_db_url;
	}

	public String getData_category() {
		return data_category;
	}

	public void setData_category(String data_category) {
		this.data_category = data_category;
	}

	public String getUrls_or_sql() {
		return urls_or_sql;
	}

	public void setUrls_or_sql(String urls_or_sql) {
		this.urls_or_sql = urls_or_sql;
	}

	public String getTask_describtion() {
		return task_describtion;
	}

	public void setTask_describtion(String task_describtion) {
		this.task_describtion = task_describtion;
	}

	public String getTask_create_date() {
		return task_create_date;
	}

	public void setTask_create_date(String task_create_date) {
		this.task_create_date = task_create_date;
	}

	public String getTask_update_time() {
		return task_update_time;
	}

	public void setTask_update_time(String task_update_time) {
		this.task_update_time = task_update_time;
	}

	public String getTask_status() {
		return task_status;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public String getDownload_thread_num() {
		return download_thread_num;
	}

	public void setDownload_thread_num(String download_thread_num) {
		this.download_thread_num = download_thread_num;
	}

}
