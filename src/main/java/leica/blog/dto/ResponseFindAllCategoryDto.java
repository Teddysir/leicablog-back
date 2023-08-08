package leica.blog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseFindAllCategoryDto {
    private Long id;
    private String name;
    private String parentName;
}
