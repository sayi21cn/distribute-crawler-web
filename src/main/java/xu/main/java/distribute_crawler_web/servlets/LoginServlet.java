package xu.main.java.distribute_crawler_web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import xu.main.java.distribute_crawler_common.util.StringHandler;
import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(LoginServlet.class);

	private String loginPassword = "";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		if ("test".equals(userName) && "test".equals(password)) {
			request.getSession().setAttribute(CrawlerWebConf.LOGIN_SESSION_KEY, "test");
			response.sendRedirect(CrawlerWebConf.ADMIN_INDEX_PATH);

			return;
		}

		if (StringHandler.isNullOrEmpty(loginPassword)) {
			this.loginPassword = userName + "-" + password;
			logger.debug("loginPassword : " + loginPassword);
			response.sendRedirect(CrawlerWebConf.LOGIN_HTML_PATH);
			return;
		}

		if ("admin".equals(userName) && loginPassword.equals(password)) {
			request.getSession().setAttribute(CrawlerWebConf.LOGIN_SESSION_KEY, "admin");
			response.sendRedirect(CrawlerWebConf.ADMIN_INDEX_PATH);
			return;
		}
		this.loginPassword = userName + "-" + password;
		logger.debug("loginPassword : " + loginPassword);
		response.sendRedirect(CrawlerWebConf.LOGIN_HTML_PATH);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

	}

}
