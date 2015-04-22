package xu.main.java.distribute_crawler_web.request_processor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;

public abstract class AbstractRequestProcessor {

	private final String contentType = "text/html;charset=utf-8";

	public abstract void process(HttpServletRequest request, HttpServletResponse response);

	protected void output(HttpServletResponse response, String output) {
		try {
			response.setStatus(200);
			response.setContentType(contentType);
			response.getWriter().write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected boolean isLogin(HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute(CrawlerWebConf.LOGIN_SESSION_KEY);
		if ("admin".equals(userName)) {
			return true;
		}
		return false;
	}

	protected Logger logger = Logger.getLogger(AbstractRequestProcessor.class);

}
