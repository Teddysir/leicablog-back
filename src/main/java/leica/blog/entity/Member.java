package leica.blog.entity;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String nickName;

    private String email;

    private String password; // add

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer loginType;


    // add
    private String provider;
    @Column(name = "provider_id")
    private String providerId;
    private LocalDateTime createDate;

    @Builder
    public Member(String username, String email, Role role, String provider, String providerId, LocalDateTime createDate,String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
        this.password = password;
    }
}


