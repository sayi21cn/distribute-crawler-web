package xu.main.java.distribute_crawler_web.request_processor.task;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.dao.TaskDao;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;
import xu.main.java.distribute_crawler_web.util.GsonUtil;


public class TaskListProcessor extends AbstractRequestProcessor {

	private TaskDao taskDao = new TaskDao();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		List<Object> taskList = taskDao.queryAllTasks();
		if (null == taskList) {
			output(response, "未查询到数据！");
			return;
		}
		output(response, GsonUtil.toJson(taskList));

	}

}
