//package leica.blog.service;
//
//
//import leica.blog.dto.PrincipalDetails;
//import leica.blog.config.auth.GoogleUserInfo;
//import leica.blog.entity.Member;
//import leica.blog.entity.Role;
//import leica.blog.repository.MemberRepository;
//import leica.blog.repository.OAuth2UserInfo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class PrincipalOauthMemberService extends DefaultOAuth2UserService {
//
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    private  MemberRepository memberRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
//        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
//        System.out.println("getAttributes: " + super.loadUser(userRequest).getAttributes());
//
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//
//        OAuth2UserInfo oAuth2UserInfo = null;
//
//        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
//            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
//        } else {
//            System.out.println("지원하지 않은 로그인 서비스 입니다.");
//        }
//
//        String provider = oAuth2UserInfo.getProvider();
//        String providerId = oAuth2UserInfo.getProviderId();
//        String username = provider + "_" + providerId;
//        String password = bCryptPasswordEncoder.encode("1234");
//        String email = oAuth2UserInfo.getEmail();
//        Role role = Role.USER;
//
//        Member memberEntity = memberRepository.findByUsername(username);
//        if (memberEntity == null) {
//            LocalDateTime createTime = LocalDateTime.now();
//            memberEntity = Member.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(role)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .createDate(createTime)
//                    .build();
//
//            memberRepository.save(memberEntity);
//        }
//        return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
//    }
//
//}
