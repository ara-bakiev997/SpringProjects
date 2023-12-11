package edu.jwt.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import edu.jwt.security.JWTUtil;
import edu.jwt.services.PersonDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final PersonDetailsService personDetailsService;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");

        if (Objects.nonNull(authorization)
            &&
            !authorization.isBlank()
            &&
            authorization.startsWith("Bearer ")
        ) {
            final String jwt = authorization.substring(7);
            if (jwt.isBlank()) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT token in Bearer header"
                );
            } else {
                try {
                    final String username = jwtUtil.validateTokenAndRetrieveClaim(jwt);
                    final UserDetails userDetails = personDetailsService.loadUserByUsername(username);

                    final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                } catch (final JWTVerificationException exception) {
                    response.sendError(
                            HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid JWT token"
                    );
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
