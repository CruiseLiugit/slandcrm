package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CharSetFilter implements Filter {
	private static Log log = LogFactory.getLog(CharSetFilter.class);
	/*
	 * http://hi.baidu.com/ae6623/item/a006f07f0813c615d1dcb366
	 注意 : ignore这个参数就是说,忽略掉页面默认编码,统一采用web.xml中的字符集配置,强制转码!!
这个ignore参数,自己思考了好久好久才发现是这个用途,靠,记忆力果然不可靠,不写注释害人匪浅啊~~
记住,过滤器的xml配置要优先放于最前边,放在<servlet>之前,才能生效!
	 */
	private boolean ignore = true;
	private String encoding;
	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		String value = filterConfig.getInitParameter("ignore");
		// ignore就是说,忽略掉页面默认编码,统一采用web.xml中的字符集配置,强制转码
		if (value == null) {
			this.ignore = true;
		} else if (value.equalsIgnoreCase("true")) {
			this.ignore = true;
		} else {
			this.ignore = false;
		}
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		log.info("开始过滤");
		encoding = getUserEncoding();
		if (ignore && encoding == null) {
			encoding = filterConfig.getInitParameter("encoding");
		}
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}
		filterChain.doFilter(request, response);
	}

	// 用户项目中定义字符集(配置文件中、在session中获取)
	public String getUserEncoding() {
		return null;
	}

	public void destroy() {

	}

}
