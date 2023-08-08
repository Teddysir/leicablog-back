package leica.blog.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestCreateCategoryDto {
    String parentName;
    String name;
}
