package leica.blog.entity;

import javax.persistence.*;

@Entity
public class User_Role {

    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Member member_id;

    @OneToOne
    private Role role_id;
}
