package xu.main.java.distribute_crawler_web.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_web.dao.DBTableInit;
import xu.main.java.distribute_crawler_web.request_processor.AbstractRequestProcessor;
import xu.main.java.distribute_crawler_web.request_processor.RequestProcessorFactory;

/***
 * 
 */

public class ServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/*
	 * static {
	 * 
	 * Envirement.getInstance().setProjectName("term-preprocessor-web");
	 * PropertyConfigurator
	 * .configure(PathUtil.getWholePath("etc/log4j.properties"));
	 * Envirement.getInstance().printApplicationInfo();
	 * 
	 * }
	 */

	@Override
	public void init() throws ServletException {
		DBTableInit.createTable();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getParameter("method");
		AbstractRequestProcessor processor = RequestProcessorFactory.getInstance().createProcessor(method);
		processor.process(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		doPost(request, response);

	}
}
