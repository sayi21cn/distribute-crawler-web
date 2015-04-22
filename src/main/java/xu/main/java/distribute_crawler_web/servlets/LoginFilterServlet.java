package xu.main.java.distribute_crawler_web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_common.util.StringHandler;
import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;

public class LoginFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = (String) request.getSession().getAttribute(CrawlerWebConf.LOGIN_SESSION_KEY);
		if (StringHandler.isNullOrEmpty(userName)) {
			try {
				response.sendRedirect(CrawlerWebConf.LOGIN_HTML_PATH);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
