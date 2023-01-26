package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDetailsDTO;
import managedBean.LoginBean;

@WebFilter("/adminFilter/*")
public class AdminFilter implements Filter {

	public static final String USER_PAGE = "/user.xhtml";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		System.out.println("filter called");
		UserDetailsDTO details = (UserDetailsDTO) httpServletRequest.getSession().getAttribute("userDetailsDTO");

		if (details.getRole().equals("admin")) {
			System.out.println("admin logged");
			filterChain.doFilter(servletRequest, servletResponse);
		}else {
			System.out.println("regular user logged");
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + USER_PAGE);			
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {

	}
}