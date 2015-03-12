package xu.main.java.distribute_crawler_web.vo;

public class TaskListVO {

	private int id;
	private String task_name;
	private int rule_pattern_id;
	private String status;
	private String insert_date;
	private String remarks;
	private String source_file_name;
	private String local_save_path;
	private String local_save_path_processed;

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

	public int getRule_pattern_id() {
		return rule_pattern_id;
	}

	public void setRule_pattern_id(int rule_pattern_id) {
		this.rule_pattern_id = rule_pattern_id;
	}

	public String getInsert_date() {
		return insert_date;
	}

	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSource_file_name() {
		return source_file_name;
	}

	public void setSource_file_name(String source_file_name) {
		this.source_file_name = source_file_name;
	}

	public String getLocal_save_path() {
		return local_save_path;
	}

	public void setLocal_save_path(String local_save_path) {
		this.local_save_path = local_save_path;
	}

	public String getLocal_save_path_processed() {
		return local_save_path_processed;
	}

	public void setLocal_save_path_processed(String local_save_path_processed) {
		this.local_save_path_processed = local_save_path_processed;
	}

}
