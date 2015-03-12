package xu.main.java.distribute_crawler_web.request_processor.template;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.dao.TemplateDao;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;
import xu.main.java.util.GsonUtil;


public class ExtractorTemplateListProcessor extends AbstractRequestProcessor {

	private TemplateDao templateDao = new TemplateDao();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		List<Object> templateList = templateDao.queryAllTemplates();
		// for(int index=0,len=templateList.size();index<len;index++){
		// TemplateVO templateVo = (TemplateVO) templateList.get(index);
		// }
		output(response, GsonUtil.toJson(templateList));

	}

}
