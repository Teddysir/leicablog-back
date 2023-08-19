package leica.blog.dto;

import leica.blog.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ResponseFindAllCategoryDto {
    private Long id;
    private String name;
    private List<ResponseFindAllCategoryDto> children;

    public static ResponseFindAllCategoryDto of(Category category) {
        return new ResponseFindAllCategoryDto(
                category.getId(),
                category.getName(),
                category.getChildren().stream().map(ResponseFindAllCategoryDto::of).collect(Collectors.toList())
        );
    }
}
