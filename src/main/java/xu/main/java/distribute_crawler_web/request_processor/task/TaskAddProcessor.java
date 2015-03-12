package xu.main.java.distribute_crawler_web.request_processor.task;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;
import xu.main.java.distribute_crawler_web.dao.TaskDao;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;


public class TaskAddProcessor extends AbstractRequestProcessor {

	private TaskDao taskDao = new TaskDao();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		try {
			String taskName = new String(request.getParameter("task_name").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String templateId = new String(request.getParameter("template_id").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String insertDbTableName = new String(request.getParameter("insert_db_table_name").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String isUseDbUrl = new String(request.getParameter("is_use_db_url").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String dataCategory = new String(request.getParameter("data_category").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String urlsOrSql = new String(request.getParameter("urls_or_sql").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String taskDescribtion = new String(request.getParameter("task_describtion").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			String downloadThreadNum = new String(request.getParameter("download_thread_num").getBytes(CrawlerWebConf.NET_DATA_CODE_CATEGORY), CrawlerWebConf.LOCAL_CODE_CATEGORY);
			
			int result = taskDao.saveTaskToDb(taskName, templateId, insertDbTableName, isUseDbUrl, dataCategory, urlsOrSql, taskDescribtion,downloadThreadNum);
			output(response, "保存任务成功,共有任务"+result+"条。");
			return;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output(response, "保存任务失败！");
	}

}
