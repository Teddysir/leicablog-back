package leica.blog.service;

import leica.blog.dto.LoginRequestDto;
import leica.blog.entity.Member;
import leica.blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member login(LoginRequestDto req){
        Optional<Member> optionalMember = memberRepository.findByEmail(req.getEmail());

        if(optionalMember.isEmpty()) {
            return null;
        }

        Member member = optionalMember.get();

        if(!member.getPassword().equals(req.getPassword())) {
            return null;
        }

        return member;
    }

    public Member getLoginMemberById(Long userId){
        if (userId ==null) return null;

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(optionalMember.isEmpty()) return null;

        return optionalMember.get();
    }

    public Member getLoginMemberByEmail(String email) {
        if (email == null) return null;

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if(optionalMember.isEmpty()) return null;

        return optionalMember.get();
    }
}
