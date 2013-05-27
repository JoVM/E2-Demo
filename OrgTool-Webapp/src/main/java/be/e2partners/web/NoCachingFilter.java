package be.e2partners.web;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: peeteth
 * Date: 10/05/13
 * Time: 9:30
 * This filter serves to avoid ViewExpiredException from JSF when the session is expired
 */

public class NoCachingFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
                                                                            //TODO: jsf 2: ResourceHandler.RESOURCE_IDENTIFIER
        if (!request.getRequestURI().startsWith(request.getContextPath() + "/javax.faces.resource")) { // Skip JSF resources (CSS/JS/Images/etc)
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
