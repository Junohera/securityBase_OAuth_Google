
package com.juno.club.config;

import com.juno.club.security.handler.CustomAuthenticationFailureHandler;
import com.juno.club.security.handler.CustomAuthenticationSuccessHandler;
import com.juno.club.security.service.ClubUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClubUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout();
        http.oauth2Login().successHandler(successHandler()).failureHandler(failureHandler());
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService);  //7days
    }

    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler(passwordEncoder());
    }

    @Bean
    public CustomAuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("user1") //사용자 계정은 user1
//                .password("$2a$10$qbTVRGiC8RePIsMz4z/QP.LjBmLOMGXBCkmW2comzfNaoeidd5/aa") //1111 패스워드 인코딩
//                .roles("USER");
//
//    }


}
