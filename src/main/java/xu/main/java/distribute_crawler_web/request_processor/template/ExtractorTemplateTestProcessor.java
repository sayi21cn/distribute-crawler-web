package xu.main.java.distribute_crawler_web.request_processor.template;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_common.vo.TemplateContentVO;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;
import xu.main.java.extractor.IExtractor;
import xu.main.java.util.GsonUtil;
import xu.main.java.util.HttpDownload;

public class ExtractorTemplateTestProcessor extends AbstractRequestProcessor {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {

		String templateContent = request.getParameter("template_content");
		String templateTestUrl = request.getParameter("template_test_url");

		System.out.println(templateContent);
		System.out.println(templateTestUrl);

		TemplateContentVO templateVo = GsonUtil.fromJson(templateContent, TemplateContentVO.class);

		System.out.println("download: " + templateTestUrl);
		String html = HttpDownload.download(templateTestUrl, "gb2312");
		
		// TODO:后期需要修改为数据库读取传入
		IExtractor extractor = ExtractorFactory.getInstance().getExtractor("cssExtractor");
		extractor.extractorColumns(html, templateVo.getHtmlPathList());
		Map<String, String> resultMap = extractor.extractorColumns(html, templateVo.getHtmlPathList());

		String result = GsonUtil.toJson(resultMap);
		output(response, result);

	}
}
