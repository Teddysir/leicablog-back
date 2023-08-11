//package leica.blog.service;
//
//import leica.blog.config.auth.jwt.JwtTokenInfo;
//import leica.blog.config.auth.jwt.JwtTokenProvider;
//import leica.blog.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Service
//@RequiredArgsConstructor
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtTokenInfo login(String username ,String password) {
//        // 토큰 생성
//        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(userToken);
//        JwtTokenInfo jwtTokenInfo = jwtTokenProvider.generateToken(authenticate);
//
//        return jwtTokenInfo;
//
//    }
//}
