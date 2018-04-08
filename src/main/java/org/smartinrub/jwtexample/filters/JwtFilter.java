package org.smartinrub.jwtexample.filters;

import static org.smartinrub.jwtexample.utils.SecurtityConstants.HEADER_STRING;
import static org.smartinrub.jwtexample.utils.SecurtityConstants.JWT_SECRET;
import static org.smartinrub.jwtexample.utils.SecurtityConstants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtFilter extends GenericFilterBean {

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
					.setSigningKey(JWT_SECRET)
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
