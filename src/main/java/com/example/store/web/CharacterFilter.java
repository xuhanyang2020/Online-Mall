package com.example.store.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "CharacterFilter")
public class CharacterFilter implements Filter {
    String encoding;
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }
}
