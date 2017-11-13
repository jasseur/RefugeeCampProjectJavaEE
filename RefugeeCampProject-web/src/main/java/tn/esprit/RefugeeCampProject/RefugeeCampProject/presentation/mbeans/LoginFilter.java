package tn.esprit.RefugeeCampProject.RefugeeCampProject.presentation.mbeans;

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

import tn.esprit.RefugeeCampProject.Types.Role;

@WebFilter("/RegistrationManagment/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest =(HttpServletRequest) request;
		HttpServletResponse servletResponse =(HttpServletResponse)response;
		
		LoginBean loginBean=(LoginBean) servletRequest.getSession().getAttribute("loginBean");
		if(loginBean !=null && loginBean.getMember().getRole()==Role.MembershipManager && loginBean.isLoggedIn()|| 
		servletRequest.getRequestURI().toString().contains("login.jsf")){
			chain.doFilter(servletRequest, servletResponse);
		}else{
			servletResponse.sendRedirect(servletRequest.getContextPath() + "/login.jsf");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
}
