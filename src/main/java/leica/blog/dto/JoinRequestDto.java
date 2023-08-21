package leica.blog.dto;

import leica.blog.entity.Member;
import leica.blog.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequestDto {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String email;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

    @NotBlank(message = "유저명이 비어있습니다.")
    private String username;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .role(UserRole.ADMIN)
                .build();
    }

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .password(encodedPassword)
                .username(this.username)
                .role(UserRole.ADMIN)
                .build();
    }
}
