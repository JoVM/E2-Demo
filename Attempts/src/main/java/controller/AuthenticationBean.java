/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

//import com.atlassian.crowd.integration.springsecurity.user.CrowdUserDetails;
//import com.realdolmen.jpp.cst.exception.NoDivisionException;
//import com.realdolmen.jpp.cst.exception.NoProfileException;
//import com.realdolmen.jpp.cst.web.jsf.util.JSFUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
//import org.springframework.security.BadCredentialsException;
//import org.springframework.security.context.SecurityContext;
//import org.springframework.security.context.SecurityContextHolder;
//import org.springframework.security.ui.AbstractProcessingFilter;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;

/**
 * 
 * @author FGSHYX5
 */
//@Component
//@Scope("request")
//@Qualifier("authenticationBean")
public class AuthenticationBean {
//	private static final Logger LOG = Logger.getLogger(AuthenticationBean.class);
//	private String username = "";
//	private String password = "";
//	private boolean rememberMe = false;
//	private boolean loggedIn = false;
//
//	public String doLogin() throws IOException, ServletException {
//		LOG.debug("Login attempt started.");
//		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//
//		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
//				.getRequestDispatcher("/j_spring_security_check");
//		try {
//			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//		} catch (NoDivisionException ex) {
//			LOG.debug("User: " + ex.getUser() + " hasn't got a division yet.");
//			JSFUtils.addMessageWithArguments(FacesMessage.SEVERITY_ERROR, "login", "no.division", new String[] { ex
//					.getUser() });
//			return "noDiv";
//		} catch (NoProfileException ex) {
//			LOG.debug("User: " + ex.getUser() + " hasn't got a profile yet.");
//			JSFUtils.addMessageWithArguments(FacesMessage.SEVERITY_ERROR, "login", "no.profile", new String[] { ex
//					.getUser() });
//			return "noDiv";
//		}
//
//		FacesContext.getCurrentInstance().responseComplete();
//		return "home";
//	}
//
//	public String getLoggedInUser() {
//		SecurityContext ctx = SecurityContextHolder.getContext();
//
//		if (ctx.getAuthentication() != null) {
//			if (ctx.getAuthentication().getPrincipal() instanceof CrowdUserDetails) {
//				CrowdUserDetails userDetails = (CrowdUserDetails) ctx.getAuthentication().getPrincipal();
//				return userDetails.getFirstName() + " " + userDetails.getLastName();
//			} else {
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}
//
//	@PostConstruct
//	public void handleErrorMessage() {
//		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
//				AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);
//
//		if (e instanceof BadCredentialsException) {
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
//					AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);
//
//			// TODO this does not work for some reason
//			FacesContext.getCurrentInstance().addMessage("loginForm:login",
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", null));
//		}
//	}
//
//	public String getUsername() {
//		return this.username;
//	}
//
//	public void setUsername(final String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return this.password;
//	}
//
//	public void setPassword(final String password) {
//		this.password = password;
//	}
//
//	public boolean isRememberMe() {
//		return this.rememberMe;
//	}
//
//	public void setRememberMe(final boolean rememberMe) {
//		this.rememberMe = rememberMe;
//	}
//
//	public boolean isLoggedIn() {
//		return this.loggedIn;
//	}
//
//	public void setLoggedIn(final boolean loggedIn) {
//		this.loggedIn = loggedIn;
//	}
//
//	public String doLogout() {
//		// this.clear();
//		FacesContext fc = FacesContext.getCurrentInstance();
//		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//		session.invalidate();
//
//		SecurityContext ctx = SecurityContextHolder.getContext();
//		ctx.getAuthentication().setAuthenticated(false);
//		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("app.user.name");
//
//		return "home";
//	}
}
