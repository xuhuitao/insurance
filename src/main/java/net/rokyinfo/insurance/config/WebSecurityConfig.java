package net.rokyinfo.insurance.config;


import net.rokyinfo.insurance.filter.JwtAuthenticationFilter;
import net.rokyinfo.insurance.filter.JwtLoginFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 *
 * @author zhaoxinguo on 2017/9/13.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭cors csrf验证
        http.cors().and().csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 对所有资源文件放行
                .antMatchers("/res/**").permitAll()
                // 对产品列表放行
                .antMatchers(HttpMethod.GET,"/v1.0/products").permitAll()
                // 对解决方案列表放行
                .antMatchers(HttpMethod.GET,"/v1.0/solutions").permitAll()
                // 对订单列表放行
                .antMatchers(HttpMethod.GET,"/v1.0/orders").permitAll()
                .antMatchers(HttpMethod.GET,"/v1.0/orders/payinfo-web").permitAll()
                .antMatchers(HttpMethod.PUT,"/v1.0/orders/update").permitAll()
                // 对订单列表放行
                .antMatchers(HttpMethod.POST,"/v1.0/orders").permitAll()
                // 对注册接口放行
                .antMatchers(HttpMethod.POST, "/v1.0/users/save").permitAll()
                .antMatchers(HttpMethod.GET, "/v1.0/orders/excel").permitAll()
                .antMatchers(HttpMethod.GET, "/v1.0/ebike/detail").permitAll()
                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
