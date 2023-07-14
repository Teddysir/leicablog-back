package leica.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    @Id @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    private String role;

}
