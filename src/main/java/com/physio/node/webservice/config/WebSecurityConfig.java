package com.physio.node.webservice.config;

import com.physio.node.webservice.jwt.JwtAuthorizationFilter;
import com.physio.node.webservice.jwt.JwtTokenProvider;
import com.physio.node.webservice.service.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(JwtTokenProvider tokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.cors().and().authorizeRequests().antMatchers("/**").permitAll();
        //Cross-origin-resource-sharing
        http.cors().and()
                .authorizeRequests()
                //These are public pages.
                .antMatchers("/user/login",
                        "/user/registration",
                        "/user/roleManagement/**",
                        "/user/{id}",
                        "/user/passwordTokenCheck/{token}",
                        "/user/passwordTokenGenerate/{email}",
                        "/error","/test",
                        "/test/**",
                        "/group/all/{id}",
                        "/message/**",
                        "/auth/**",
                        "/fileSystem/**",
                        "/visit-system/**",
                        "/exercise/**")
                .permitAll()
                .antMatchers("/ailment/user/{id}",
                        "/ailment/{id}","/group/addUserToGroup/{userId}/{groupId}",
                        "/group/removeUserFromGroup/{userId}/{groupId}",
                        "/error")
                .hasAnyRole("admin","user")
                //These can be reachable for just have admin role.
//                .antMatchers("/group/all/{id}",
//                        "/group/{id}",
//                        "/group/userList/{id}",
//                        "/user/{id}").hasRole("admin")
                //all remaining paths should need authentication.
                .anyRequest().hasRole("admin")
                .and()
                //logout will log the user out by invalidate session.
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST")).and()
                //login form and path
                .formLogin().loginPage("/api/user/login").and()
                //enable basic authentication. Http header: basis username:password
                .httpBasic().and()
                //Cross side request forgery.
                .csrf().disable();
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Cross origin resource sharing.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

}
