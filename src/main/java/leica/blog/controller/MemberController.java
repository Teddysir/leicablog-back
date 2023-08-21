package leica.blog.controller;

import leica.blog.dto.LoginRequestDto;
import leica.blog.entity.Member;
import leica.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpServletRequest http){
        Member member = memberService.login(dto);

        if(member == null) {
            return ResponseEntity.ok("로그인 아이디 또는 비밀번호가 틀렸습니다.");
        }

        http.getSession().invalidate();
        HttpSession session = http.getSession(true);

        session.setAttribute("userId",member.getId());
        session.setMaxInactiveInterval(3600); // 1H

        return ResponseEntity.ok("로그인되었습니다.");

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "로그아웃되었습니다.";
    }
}
