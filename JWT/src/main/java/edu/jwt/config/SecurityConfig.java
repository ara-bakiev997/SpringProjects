package edu.jwt.config;

import edu.jwt.services.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz ->
                    authz
                            .requestMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                            .anyRequest().hasAnyRole("USER", "ADMIN")
            )
            .formLogin(configurer -> configurer.permitAll())
            .formLogin(configurer -> configurer.loginPage("/auth/login")
                                               .loginProcessingUrl("/process_login")
                                               .defaultSuccessUrl("/hello", true)
                                               .failureUrl("/auth/login?error"))
            .logout(configurer -> configurer.logoutUrl("/logout").logoutSuccessUrl("/auth/login"));

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(personDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
