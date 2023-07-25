package leica.blog.config.security;

import leica.blog.api.repository.user.UserRefreshTokenRepository;
import leica.blog.config.properties.AppProperties;
import leica.blog.config.properties.CorsProperties;
import leica.blog.oauth.entity.RoleType;
import leica.blog.oauth.exception.RestAuthenticationEntryPoint;
import leica.blog.oauth.filter.TokenAuthenticationFilter;
import leica.blog.oauth.handler.OAuth2AuthenticationFailureHandler;
import leica.blog.oauth.handler.OAuth2AuthenticationSuccessHandler;
import leica.blog.oauth.handler.TokenAccessDeniedHandler;
import leica.blog.oauth.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import leica.blog.oauth.service.CustomOAuth2UserService;
import leica.blog.oauth.service.CustomUserDetailsService;
import leica.blog.oauth.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsProperties corsProperties;
    private final AppProperties appProperties;
    private final AuthTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final CustomOAuth2UserService oAuth2UserService;
    private final TokenAccessDeniedHandler tokenAccessDeniedHandler;
    private final UserRefreshTokenRepository userRefreshTokenRepository;

    /*
     * UserDetailsService 설정
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Restful api를 구현하기 위해 사용
                    .and()
                    .csrf().disable() // CSRF(Cross-Site Request Forgery) 보호 기능을 비활성화
                    .formLogin().disable() // 폼 로그인을 비활성화합니다.
                    .httpBasic().disable() // Http basic 인증 비활성화
                .exceptionHandling() //예외처리 핸들링
                    .authenticationEntryPoint(new RestAuthenticationEntryPoint()) // 인증되지 않은 요청에 대한 처리를 담당하는 엔트리 포인트(진입점)
                    .accessDeniedHandler(tokenAccessDeniedHandler) //인가 거부된 요청에 대한 처리
                .and()
                .authorizeRequests() // 인가에 대한처리
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //cors 사전요청에 대해 인가없이 허용
                    .antMatchers("/api/**").hasAnyAuthority(RoleType.USER.getCode())//getCode : ROLE_USER
                    .antMatchers("/api/**/admin/**").hasAnyAuthority(RoleType.ADMIN.getCode()) //getCode : ROLE_ADMIN
                    .anyRequest().authenticated()
                .and()
                .oauth2Login() //oauth 로그인에 대한 처리
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorization") // oauth2 인가 요청 기본 엔드포인트
                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()) // oauth2 인가요청 저장하는 커스텀 저장소
                .and()
                    .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*") //oauth 로그인 완료후 redirect되는 uri 설정 => authorization code 코드 제공
                    .and()
                    .userInfoEndpoint()
                    .userService(oAuth2UserService) // OAuth2 사용자 정보 엔드포인트와 사용자 서비스(CustomOAuth2UserService)를 연결합니다.
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler()) //OAuth2 로그인 성공 시 처리를 담당하는 커스텀 핸들러(OAuth2AuthenticationSuccessHandler)를 설정합니다.
                .failureHandler(oAuth2AuthenticationFailureHandler());

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /*
     * auth 매니저 설정
     * */
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*
     * security 설정 시, 사용할 인코더 설정
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 토큰 필터 설정
     * */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    /*
     * 쿠키 기반 인가 Repository
     * 인가 응답을 연계 하고 검증할 때 사용.
     * */
    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    /*
     * Oauth 인증 성공 핸들러
     * */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(
                tokenProvider,
                appProperties,
                userRefreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository()
        );
    }

    /*
     * Oauth 인증 실패 핸들러
     * */
    @Bean
    public OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler() {
        return new OAuth2AuthenticationFailureHandler(oAuth2AuthorizationRequestBasedOnCookieRepository());
    }

    /*
     * Cors 설정
     * */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource corsConfigSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedHeaders(Arrays.asList(corsProperties.getAllowedHeaders().split(",")));
//        corsConfig.setAllowedMethods(Arrays.asList(corsProperties.getAllowedMethods().split(",")));
//        corsConfig.setAllowedOrigins(Arrays.asList(corsProperties.getAllowedOrigins().split(",")));
//        corsConfig.setAllowCredentials(true);
//        corsConfig.setMaxAge(corsProperties.getMaxAge());
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setExposedHeaders(List.of("*"));
        corsConfig.setMaxAge(corsProperties.getMaxAge());


        corsConfigSource.registerCorsConfiguration("/**", corsConfig);
        return corsConfigSource;
    }
}