package leica.blog.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer loginType;

    @Builder
    public Member(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    public Member update(String name){
        this.name = name;

        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}


