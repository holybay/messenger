package by.it_academy.jd2.golubev_107.messenger.controller.filter;

import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AdminSecurityFilter", urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {

    private static final String SESSION_USER_ATTRIB = "user";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession();

        UserOutDto userOutDto = null;
        if (session != null && session.getAttribute(SESSION_USER_ATTRIB) != null) {
            userOutDto = castToUserOutDto(session);
        }

        if (session == null || userOutDto == null) {
            resp.sendRedirect(contextPath + "/ui/signIn");
        }

        if (userOutDto.getRole() != null && "ADMIN".equals(userOutDto.getRole().name())) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(contextPath + "/jsp/error.jsp");
        }
    }

    private UserOutDto castToUserOutDto(HttpSession session) {
        if (session.getAttribute(SESSION_USER_ATTRIB) instanceof UserOutDto user) {
            return user;
        }
        session.removeAttribute(SESSION_USER_ATTRIB);
        return null;
    }
}
