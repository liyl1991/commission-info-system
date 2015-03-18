package cn.haohao.cis.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 不拦截的url
		String[] notFilter = new String[] { "/jsp/login.jsp","/login/doLogin" };

		// 请求的url
		String url = request.getRequestURI();

		boolean doFilter = check(notFilter, url);
		if (doFilter) {
			Object obj = request.getSession().getAttribute("loginedUser");
			if (null == obj) {
				// 如果session中不存在登录者实体，则弹出框提示重新登录
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				String loginPage = request.getContextPath() + "/jsp/login.jsp";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("alert('登陆会话过时，请重新登陆！');"); 
				builder.append("window.top.location.href='");
				builder.append(loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @param notFilter不拦截的url
	 * @param url：请求的url
	 * @return false：不拦截 true：拦截
	 */
	public boolean check(String[] notFilter, String url) {
		// url以css和js结尾的不进行拦截
		if (url.endsWith(".css") 
			|| url.endsWith(".js")
			|| url.endsWith(".png")
			|| url.endsWith(".jpg")
			|| url.endsWith(".jepg")
			|| url.endsWith(".gif")
			|| url.endsWith(".url")
			|| url.endsWith(".ttf")
			|| url.endsWith(".eot")
			|| url.endsWith(".svg")
			|| url.endsWith(".woff")
			|| url.endsWith(".otf")) {
			return false;
		}
		// 含有notFilter中的任何一个则不进行拦截
		for (String s : notFilter) {
			if (url.indexOf(s) != -1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
