//package leica.blog.entity;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Post {
//
//    @Id @GeneratedValue
//    @Column(name="post_id")
//    private Long id;
//
//    private String title;
//
//    private String content;
//
//    @ManyToOne()
//    @JoinColumn(name = "category_id")
//    private Category category_id;
//
//    @OneToMany(mappedBy = "comment_id")
//    private List<Comment> comments = new ArrayList<>();
//}
