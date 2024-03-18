package cgg.spring_security.spring_security.filters;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter
public class RedirectAuthenticatedUserFilter implements Filter {
    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/signin");

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        // Check if the request is for the login page and if the user is authenticated
        if (requestMatcher.matches(request) && authentication != null && authentication.isAuthenticated()) {
            // Redirect authenticated users away from the login page
            SecurityContextHolder.getContext().setAuthentication(null);
            ;
            response.sendRedirect(request.getContextPath() + "/signin");
        } else {
            // Allow other requests to proceed
            chain.doFilter(req, res);
        }
    }
}
