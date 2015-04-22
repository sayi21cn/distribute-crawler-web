package xu.main.java.distribute_crawler_web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xu.main.java.distribute_crawler_common.util.StringHandler;
import xu.main.java.distribute_crawler_web.configure.CrawlerWebConf;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest re = (HttpServletRequest) request;
		HttpServletResponse rp = (HttpServletResponse) response;
		String userName = (String) re.getSession().getAttribute(CrawlerWebConf.LOGIN_SESSION_KEY);
		if (StringHandler.isNullOrEmpty(userName)) {
			rp.sendRedirect(CrawlerWebConf.LOGIN_HTML_PATH);
			return;
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
