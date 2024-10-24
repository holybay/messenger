package by.it_academy.jd2.golubev_107.messenger.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {

    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding(ENCODING);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(ENCODING);
        chain.doFilter(request, response);
    }
}
