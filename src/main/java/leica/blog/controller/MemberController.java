//package leica.blog.controller;
//
//import leica.blog.config.auth.jwt.JwtTokenInfo;
//import leica.blog.service.MemberService;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//public class MemberController {
//
//    private final MemberService memberService;
//
//
//    @PostMapping("/login")
//    public JwtTokenInfo login(@RequestBody RequestMemberLoginDto requestMemberLoginDto){
//        log.debug("what the...");
//        return memberService.login(requestMemberLoginDto.username, requestMemberLoginDto.password);
//    }
//
//
//    @Data
//    private static class RequestMemberLoginDto{
//        private String username;
//        private String password;
//    }
//}
