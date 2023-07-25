package leica.blog.oauth.filter;

import leica.blog.oauth.token.AuthToken;
import leica.blog.oauth.token.AuthTokenProvider;
import leica.blog.utils.HeaderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

    // 실제 비지니스 로직이 실행되기 전에 수행되야할 코드들
    // 토큰 검증, 인증과 권한 부여

    String tokenStr = HeaderUtil.getAccessToken(request);
    AuthToken token = tokenProvider.convertAuthToken(tokenStr); //토큰 검증

    if(token.validate()) {
        Authentication authentication = tokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
    }

}
