package leica.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends PostTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @Column
//    private String imageKey;

    @Builder
    public Post(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }



//    private String video; // S3올릴때 동영상은 무슨 타입으로 받을까?



//    @OneToMany(mappedBy = "comment_id")
//    private List<Comment> comments = new ArrayList<>();
}
