package leica.blog.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne
    private Post post_id;

    @ManyToOne
    private Member member;
}
