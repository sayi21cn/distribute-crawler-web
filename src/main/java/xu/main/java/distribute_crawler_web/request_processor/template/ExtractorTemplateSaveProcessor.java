package xu.main.java.distribute_crawler_web.request_processor.template;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;
import xu.main.java.distribute_crawler_web.dao.TemplateDao;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;

public class ExtractorTemplateSaveProcessor extends AbstractRequestProcessor {

	private TemplateDao templateDao = new TemplateDao();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		boolean isLogin = this.isLogin(request);
		if (!isLogin) {
			output(response, CrawlerWebConf.PERMISION_NOT_DEFINE_MESSAGE);
			return;
		}

		try {
			String templateName = new String(request.getParameter("template_name").getBytes("iso8859-1"), "utf-8");
			String templateArea = new String(request.getParameter("template_area").getBytes("iso8859-1"), "utf-8");
			String templateTestUrl = new String(request.getParameter("template_test_url").getBytes("iso8859-1"), "utf-8");
			String templateDescribe = new String(request.getParameter("template_describe").getBytes("iso8859-1"), "utf-8");
			int result = templateDao.saveTemplate(templateName, templateArea, templateTestUrl, templateDescribe);

			output(response, "保存成功共" + result + "条记录");
			return;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		output(response, "失败");
	}

}
