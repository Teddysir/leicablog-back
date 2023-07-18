package leica.blog.config.auth.dto;

import leica.blog.entity.Member;
import lombok.Getter;

@Getter
public class SessionMember {
    private String name;
    private String email;

    public SessionMember(Member member) {
        this.name = member.getUsername();
        this.email = member.getEmail();
    }
}
