package leica.blog.dto;

import leica.blog.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberDto {
    private String email;
    private String password;
    private List<String> roles;

    @Builder
    public MemberDto(String email, String password, List<String> roles){
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .roles(roles)
                .build();
    }
}
