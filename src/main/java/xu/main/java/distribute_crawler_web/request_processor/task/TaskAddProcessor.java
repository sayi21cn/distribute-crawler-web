package xu.main.java.distribute_crawler_web.request_processor.task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;
import xu.main.java.distribute_crawler_web.dao.TaskDao;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;

public class TaskAddProcessor extends AbstractRequestProcessor {

	private TaskDao taskDao = new TaskDao();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			output(response, CrawlerWebConf.PERMISION_NOT_DEFINE_MESSAGE);
			return;
		}

		try {
			String taskName = request.getParameter("task_name");
			String templateId = request.getParameter("template_id");
			String isCycleTask = request.getParameter("is_cycle_task");
			String insertDbTableName = request.getParameter("insert_db_table_name");
			String isUseDbUrl = request.getParameter("is_use_db_url");
			String dataCategory = request.getParameter("data_category");
			String urlsOrSql = request.getParameter("urls_or_sql");
			String taskDescribtion = request.getParameter("task_describtion");
			String downloadThreadNum = request.getParameter("download_thread_num");

			String cycleTaskInterval = request.getParameter("cycle_task_interval");

			int result = taskDao.saveTaskToDb(isCycleTask, taskName, templateId, insertDbTableName, isUseDbUrl, dataCategory, urlsOrSql, taskDescribtion, downloadThreadNum);
			output(response, "保存任务成功,共有任务" + result + "条。");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		output(response, "保存任务失败！");
	}

}
