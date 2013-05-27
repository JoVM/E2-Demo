
package be.e2partners.controller.login;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
//import org.springframework.security.AuthenticationException;
//import org.springframework.security.context.SecurityContext;
//import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.ui.AbstractProcessingFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import be.e2partners.util.JSFUtils;

import com.atlassian.crowd.integration.springsecurity.user.CrowdUserDetails;


@Component
@Scope("request")
@Qualifier("authenticationBean")
public class AuthenticationBean {
	private static final Logger LOG = Logger.getLogger(AuthenticationBean.class);
	private String username = "";
	private String password = "";
	private boolean rememberMe = false;
	private boolean loggedIn = false;

    private static final String ORGTOOL_LOGIN_ERROR = "ORGTOOL_LOGIN_ERROR";

	public String doLogin() throws IOException, ServletException {
		LOG.debug("Login attempt started.");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					ORGTOOL_LOGIN_ERROR, null);

		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_security_check");
		try {
			dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
            handleErrorMessage();
        } catch (AuthenticationException ex) {
//            JSFUtils.addMessageDirect(FacesMessage.SEVERITY_ERROR, ex.getMessage());
            return "noRights";
        }
		FacesContext.getCurrentInstance().responseComplete();
//        FacesContext.getCurrentInstance().getExternalContext().res

		return "home";
	}

	public String getLoggedInUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx.getAuthentication() != null) {
			if (ctx.getAuthentication().getPrincipal() instanceof CrowdUserDetails) {
				CrowdUserDetails userDetails = (CrowdUserDetails) ctx.getAuthentication().getPrincipal();
				return userDetails.getFirstName() + " " + userDetails.getLastName();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@PostConstruct
	public void handleErrorMessage() throws ServletException, AuthenticationException {
		Exception e = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
                WebAttributes.AUTHENTICATION_EXCEPTION);

//        Object test = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(
//                UsernamePasswordAuthenticationFilter.SPRING_SECURITY_SAVED_REQUEST_KEY);

        if (e != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);



			// TODO this does not work for some reason
			JSFUtils.addMessageDirect(FacesMessage.SEVERITY_ERROR, "Invalid Login");

            if(!(e instanceof AuthenticationException)){
                throw new RuntimeException(e);
            }else {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
					ORGTOOL_LOGIN_ERROR, e.getMessage()); //TODO: customize msg

                AuthenticationException ae = (AuthenticationException) e;
                throw new RuntimeException(e);
//                throw ae;
            }
		}

//		if (e instanceof BadCredentialsException) {
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
//					AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, null);
//
//			// TODO this does not work for some reason
//			FacesContext.getCurrentInstance().addMessage("loginForm:login",
//					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password not valid.", null));
//            throw e;
//		}else
//        if(e != null){
//            throw e;
//        }
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(final boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

    public String doLogout() {
		// this.clear();
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();

		SecurityContext ctx = SecurityContextHolder.getContext();
		ctx.getAuthentication().setAuthenticated(false);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("app.user.name");

		return "home";
	}
}
