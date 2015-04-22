package xu.main.java.distribute_crawler_web.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_common.vo.TaskRecord;
import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;
import xu.main.java.distribute_crawler_web.util.DbUtil;

public class TaskDao {
	private DbUtil dbUtils = new DbUtil(CrawlerWebConf.JDBC_DB_NAME);

	private Logger logger = Logger.getLogger(TaskDao.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final String SAVE_TASK_SQL = "insert into task (task_name,template_id,insert_db_table_name,is_use_db_url,data_category,urls_or_sql,task_describtion,download_thread_num,task_create_date,task_update_time) values (?,?,?,?,?,?,?,?,?,?);";

	// 循环任务插入SQL
	private final String SAVE_CYCLE_TASK_SQL = "insert into cycle_task (task_name,template_id,insert_db_table_name,is_use_db_url,data_category,urls_or_sql,task_describtion,download_thread_num,task_create_date,task_update_time) values (?,?,?,?,?,?,?,?,?,?);";

	private final String QUERY_ALL_TASKS = "select id,task_name,template_id,insert_db_table_name,is_use_db_url,data_category,urls_or_sql,task_describtion,task_create_date,task_update_time,download_thread_num,task_status from task order by id desc";

	public int saveTaskToDb(String task_type, String task_name, String template_id, String insert_db_table_name, String is_use_db_url, String data_category, String urls_or_sql, String task_describtion, String download_thread_num) {

		List<String> dataList = Arrays.asList(task_name, template_id, insert_db_table_name, is_use_db_url, data_category, urls_or_sql, task_describtion, download_thread_num, getFormatCurrentDate(), getFormatCurrentDate());
		// 循环任务
		if ("1".equals(task_type)) {
			return dbUtils.executeInsert(SAVE_CYCLE_TASK_SQL, dataList);
		}
		return dbUtils.executeInsert(SAVE_TASK_SQL, dataList);
	}

	public List<Object> queryAllTasks() {
		try {
			return dbUtils.executeSQL(QUERY_ALL_TASKS, TaskRecord.class);
		} catch (SQLException e) {
			logger.error("查询所有Task任务出错！", e);
		}
		return null;
	}

	private String getFormatCurrentDate() {
		return sdf.format(System.currentTimeMillis());
	}

}
