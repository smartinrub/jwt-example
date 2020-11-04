package org.smartinrub.jwtexample.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.smartinrub.jwtexample.utils.SecurityConstants.HEADER_STRING;
import static org.smartinrub.jwtexample.utils.SecurityConstants.TOKEN_PREFIX;

public class JwtFilter implements Filter {

    private final String jwtSecret;

    public JwtFilter(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;

        final String authHeader = request.getHeader(HEADER_STRING);

        if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
            throw new ServletException("Token not found");
        }

        final String compactJws = authHeader.substring(TOKEN_PREFIX.length());

        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(compactJws)
                    .getBody();
            // We can trust this JWT
            request.setAttribute("username", claims.get("username"));
        } catch (Exception e) {
            // don't trust the JWT!
            throw new JwtException("Invalid JWT token", e);
        }

        chain.doFilter(req, res);
    }

}
