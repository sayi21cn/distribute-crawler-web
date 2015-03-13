package xu.main.java.distribute_crawler_web.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_common.vo.TaskListVO;
import xu.main.java.distribute_crawler_common.vo.TemplateVO;
import xu.main.java.distribute_crawler_web.configure.TermPreprocessorWebConfigure;
import xu.main.java.distribute_crawler_web.util.DbUtil;


public class TemplateDao {
	private DbUtil dbUtils = new DbUtil(TermPreprocessorWebConfigure.JDBC_DB_NAME);

	private Logger logger = Logger.getLogger(TemplateDao.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final String SAVE_TEMPLATE_SQL = "insert into template (template_name,template_area,template_test_url,template_describe,template_create_time,template_update_time) values (?,?,?,?,?,?)";

	private final String QUERY_BY_TASK_ID_SQL = "select id,task_name,rule_pattern_id,insert_date,status,remarks,source_file_name,local_save_path,local_save_path_processed from task_list where is_delete=0 and id = ?";

	private final String QUERY_ALL_TEMPLATE = "select id,template_name,template_area,template_test_url,template_describe,template_update_number,template_create_time,template_update_time from template";

	public int saveTemplate(String templateName, String templateArea, String templateTestUrl, String templateDescribe) {
		List<String> dataList = Arrays.asList(templateName, templateArea, templateTestUrl, templateDescribe, getFormatCurrentDate(), getFormatCurrentDate());
		return dbUtils.executeInsert(SAVE_TEMPLATE_SQL, dataList);
	}

	public List<Object> getTaskByTaskId(String taskId) {
		try {
			return dbUtils.executeSQL(QUERY_BY_TASK_ID_SQL, TaskListVO.class, taskId);
		} catch (SQLException e) {
			logger.error("QUERY DB ERROR", e);
		}
		return null;
	}

	public List<Object> queryAllTemplates() {
		try {
			return dbUtils.executeSQL(QUERY_ALL_TEMPLATE, TemplateVO.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getFormatCurrentDate() {
		return sdf.format(System.currentTimeMillis());
	}
}
