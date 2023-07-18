package leica.blog.config;

import leica.blog.entity.Member;
import lombok.Getter;

@Getter
public class SessionMember {

    private String name;
    private String email;

    public SessionMember(Member member){
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
