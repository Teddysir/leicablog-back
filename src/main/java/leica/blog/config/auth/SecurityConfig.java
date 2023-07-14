package leica.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOAuth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
            .oauth2Login()				// OAuth2 로그인
                .loginPage("/login")		// 인증이 필요한 URL에 접근하면 /login으로 이동
                .defaultSuccessUrl("/")			// 로그인 성공하면 "/" 으로 이동
                .failureUrl("/login")		// 로그인 실패 시 /login으로 이동
                .userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
                .userService(principalOauth2UserService);	//사용자정보를 처리할 때 사용한다
    }
}