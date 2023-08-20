package leica.blog.controller;

import leica.blog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private MemberService memberService;

//    @PostMapping("") 어디페이지로 리턴해야하지???
}
