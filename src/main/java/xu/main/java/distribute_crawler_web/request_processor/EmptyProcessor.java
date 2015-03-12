package xu.main.java.distribute_crawler_web.request_processor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;


public class EmptyProcessor extends AbstractRequestProcessor {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) {
		output(response, "无相应的处理类");
	}
}
