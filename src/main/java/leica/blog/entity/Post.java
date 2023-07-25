package leica.blog.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageKey;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }



//    private String video; // S3올릴때 동영상은 무슨 타입으로 받을까?

//    @ManyToOne()
//    @JoinColumn(name = "category_id")
//    private Category category_id;
//
//    @OneToMany(mappedBy = "comment_id")
//    private List<Comment> comments = new ArrayList<>();
}
