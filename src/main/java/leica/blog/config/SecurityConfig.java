//package leica.blog.config;
//
//import leica.blog.service.PrincipalOauthMemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private  PrincipalOauthMemberService principalOauthMemberService;
//
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .anyRequest().permitAll()
//
//                .and()
//                .formLogin()
//                .loginPage("/loginForm")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/")
//
//                .and()
//                .oauth2Login()
//                .loginPage("/loginForm")
//                .defaultSuccessUrl("/")
//                .userInfoEndpoint()
//                .userService(principalOauthMemberService);
//
//        return http.build();
//    }
//
//}
