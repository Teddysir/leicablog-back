package leica.blog.dto;

import leica.blog.entity.Category;
import leica.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;
    private String categoryName;
    private String parentCategoryName;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    @Builder
    public PostDto(String title, String content, String category , String parentCategoryName, Timestamp createdAt, Timestamp modifiedAt){
        this.title = title;
        this.content = content;
        this.categoryName = category;
        this.parentCategoryName = parentCategoryName;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Post toEntity(Category category){
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();
    }


}
