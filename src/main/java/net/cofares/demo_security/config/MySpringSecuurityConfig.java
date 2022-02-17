package net.cofares.demo_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * template pour ajouter la sécurité a SpringBoot
 *
 * @author Pascal Fares
 */
@Configuration
@EnableWebSecurity
public class MySpringSecuurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("pascal@exemple.com").password(passwordEcoder().encode("pascal")).roles("USER")
                .and()
                .withUser("admin@exemple.com").password(passwordEcoder().encode("admin")).roles("ADMIN", "USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
                //.httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEcoder() {
        return new BCryptPasswordEncoder();
    }
}
