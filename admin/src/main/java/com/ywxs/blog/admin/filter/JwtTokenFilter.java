package com.ywxs.blog.admin.filter;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ywxs.blog.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            final String authToken = authHeader.substring("Bearer".length());
            Claims claims = jwtUtil.parseJWT(authToken);
            String id = claims.getId();
            String role = (String) claims.get("roles");
            String username = claims.getSubject();
            JSONArray array = JSONUtil.parseArray(role);
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(10);
            for(Object o : array) {
                grantedAuthorities.add(new SimpleGrantedAuthority(o.toString()));
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    id,username , grantedAuthorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}

