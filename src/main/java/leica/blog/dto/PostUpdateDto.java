package leica.blog.dto;

import lombok.Getter;

@Getter
public class PostUpdateDto {
    private String title;
    private String content;

    public PostUpdateDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
