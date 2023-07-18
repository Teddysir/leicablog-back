package leica.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;

    private String username;

    private Integer loginType;

    @Enumerated(EnumType.STRING)
    private Role role;


    public String getRoleKey() {
        return this.role.getKey();
    }

}