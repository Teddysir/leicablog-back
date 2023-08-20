package leica.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class ResponseAllPostByCategory {
    private String thumbnail;
    private Timestamp create_at;
    private String title;
    private String content;


}
