package com.tecktree.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.tecktree.user.SocialUserDetailsServiceImpl;
import com.tecktree.user.UserDetailsServiceImpl;
import com.tecktree.user.UserRepository;

@Configuration
@EnableWebSecurity
@ImportResource("classpath:META-INF/auditing.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SocialUserDetailsServiceImpl(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userRepository);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            //Configures form signin
            .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/signin/authenticate")
                .failureUrl("/signin?error=bad_credentials")
            //Configures the logout function
            .and()
                .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutUrl("/signout")
                    .logoutSuccessUrl("/home")
            //Configures url based authorization
            .and()
                .authorizeRequests()
                    //Anyone can access the urls
                    .antMatchers(
                    		"/",
                    		"/home",
                            "/auth/**",
                            "/signin/**",
                            "/signup/**",
                            "/user/**",
                            "/disconnect/**"
                    ).permitAll()
                    //The rest of the our application is protected.
                    .antMatchers("/**").hasRole("USER")
            //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
            .and()
                .apply(new SpringSocialConfigurer());
    }
    
}
