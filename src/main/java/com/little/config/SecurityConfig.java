package com.little.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
                .requestMatchers("/miao/update").hasAuthority("admin:api")
                .requestMatchers("/miao/delete").hasAuthority("admin:api")
                .requestMatchers("/miao/insert").hasAuthority("admin:api")
                .requestMatchers("/miao/search").hasAnyAuthority("admin:api", "user:api")
                .requestMatchers("/miao/total").hasAnyAuthority("admin:api", "user:api")
                .anyRequest().authenticated()
        );

        http.formLogin(formLogin -> formLogin
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("loginSuccess");
                    System.out.println("authentication.getCredentials() =" + authentication.getCredentials());
                    System.out.println("authentication.getPrincipal() =" + authentication.getPrincipal());
                    System.out.println("authentication.getAuthorities() =" + authentication.getAuthorities());
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("LoginERROR");
                    System.out.println("登录异常信息:");
                    System.out.println(exception.getCause().getMessage());
                })
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout.invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}