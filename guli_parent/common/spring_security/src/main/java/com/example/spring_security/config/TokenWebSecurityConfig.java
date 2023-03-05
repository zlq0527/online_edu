//package com.example.spring_security.config;
//
//import com.example.spring_security.security.DefaultPasswordEncoder;
//import com.example.spring_security.security.TokenManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private RedisTemplate redisTemplate;
//    private TokenManager tokenManager;
//    private DefaultPasswordEncoder defaultPasswordEncoder;
//
//    @Autowired
//    public TokenWebSecurityConfig(RedisTemplate redisTemplate, TokenManager tokenManager, DefaultPasswordEncoder defaultPasswordEncoder) {
//        this.defaultPasswordEncoder = defaultPasswordEncoder;
//        this.tokenManager = tokenManager;
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.exceptionHandling()
//                .authenticationEntryPoint()
//                .and().csrf().disable()
//                .authorizeRequests()
//                .and().logout().logoutUrl("")
//                .addLogoutHandler().and()
//                .addFilter()
//                .addFilter();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService().passwordEncoder()
//    }
//
//    /**
//     * 配置哪些请求不拦截
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/**", "", "");
//        web.ignoring().antMatchers("");
//    }
//}
