package edu.jwt.security;

import edu.jwt.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {
    private final PersonDetailsService personDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails userDetails = personDetailsService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
        if (!userDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true; // если в приложении будет несколько аутентификатион провайдеров
    }
}
