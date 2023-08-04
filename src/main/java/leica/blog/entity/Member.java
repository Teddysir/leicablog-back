//package leica.blog.entity;
//
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Member {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "member_id")
//    private Long id;
//
//    @Column(nullable = false)
//    private String username;
//
//    @Column(nullable = false, unique = true)
//    private String nickName;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String password; // add
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    private Integer loginType;
//
//
////    // add
////    @Column(nullable = false)
////    private String provider;
//////    @Column(name = "provider_id")
////
////    @Column(nullable = false)
////    private String providerId;
////    private LocalDateTime createDate;
////
////    @Builder
////    public Member(String username, String email, Role role, String provider, String providerId, LocalDateTime createDate,String password) {
////        this.username = username;
////        this.email = email;
////        this.role = role;
////        this.provider = provider;
////        this.providerId = providerId;
////        this.createDate = createDate;
////        this.password = password;
////    }
//}
//
//
