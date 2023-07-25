package leica.blog.dto;

import leica.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    private String title;
    private String content;

    @Builder
    public PostDto(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }


}
