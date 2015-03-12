package xu.main.java.distribute_crawler_web.request_processor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class AbstractRequestProcessor {

	private final String contentType = "text/html;charset=utf-8";

	public abstract void process(HttpServletRequest request, HttpServletResponse response);

	protected void output(HttpServletResponse response, String output) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Logger logger = Logger.getLogger(AbstractRequestProcessor.class);

}
