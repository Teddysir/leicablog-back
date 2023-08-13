//package leica.blog.config.auth.jwt.filter;
//
//import leica.blog.config.auth.jwt.JwtTokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilterBean {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try{
//            // 토큰 가져오기
//            String token = resolveToken((HttpServletRequest) request);
//
//            // 토큰 유효성 검사
//            if(token != null & jwtTokenProvider.validateToken(token)){
//                // 인증 정보 가져오기!
//                Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                //SeurityContectHolder의 SecurityContext에 authentication 저장
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }finally {
//            chain.doFilter(request,response);
//        }
//
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
