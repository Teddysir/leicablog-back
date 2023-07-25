package leica.blog.api.entity.user;

import leica.blog.oauth.entity.ProviderType;
import leica.blog.oauth.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USER")
public class User {

    @JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID", length = 64, unique = true)
    @NotNull
    @Size(max=64)
    private String userId;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max=100)
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    @NotNull
    private LocalDateTime updatedAt;

    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 128) String password,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime updatedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email != null ? email : "NO_EMAIL";
        this.password = "NO_PASS";
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
