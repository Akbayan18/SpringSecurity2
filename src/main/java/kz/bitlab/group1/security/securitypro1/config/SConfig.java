package kz.bitlab.group1.security.securitypro1.config;

import kz.bitlab.group1.security.securitypro1.service.UsserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SConfig {

    @Autowired
    private UsserService usserService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(usserService)
                .passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/forbid");

        http.formLogin()
                .loginPage("/sign")
                .loginProcessingUrl("/aut")
                .defaultSuccessUrl("prof")
                .failureUrl("/sign?error")
                .usernameParameter("user_email")
                .passwordParameter("user_password");

        http.logout()
                .logoutUrl("/wayout")
                .logoutSuccessUrl("/sign");
        return http.build();
    }
}
