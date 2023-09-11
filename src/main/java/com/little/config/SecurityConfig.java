package com.little.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * authorizeHttpRequests
     * 捕捉对于 /login 的请求，使用 permitAll() 实现匿名访问
     * 而对于 anyRequest() 其他任意请求，使用 authenticated() 需认证登录
     * <p></p>
     * formLogin
     * loginPage() 登录页面
     * loginProcessingUrl() 接口 过滤器
     * defaultSuccessUrl() 登录成功后的跳转页面
     * <p></p>
     * csrf 跨域漏洞防御 disable 关闭
     * <p></p>
     * logout 退出账号
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin(formLogin -> formLogin

                .loginProcessingUrl("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write("loginSuccess");
                        System.out.println("authentication.getCredentials() =" + authentication.getCredentials());
                        System.out.println("authentication.getPrincipal() =" + authentication.getPrincipal());
                        System.out.println("authentication.getAuthorities() =" + authentication.getAuthorities());
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write("LoginERROR");
                        System.out.println("登录异常信息:");
                        System.out.println(exception.getCause().getMessage());
                    }
                })
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout.invalidateHttpSession(true));

        return http.build();
    }
}