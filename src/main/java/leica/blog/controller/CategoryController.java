package leica.blog.controller;

import leica.blog.dto.RequestCreateCategoryDto;
import leica.blog.dto.ResponseFindAllCategoryDto;
import leica.blog.entity.Category;
import leica.blog.repository.CategoryRepository;
import leica.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody RequestCreateCategoryDto requestCreateCategoryDto){
        String name = requestCreateCategoryDto.getName();
        String parentName = requestCreateCategoryDto.getParentName();
        categoryService.createCategory(name, parentName);
        return ResponseEntity.ok("created!");
    }

    @GetMapping("/findAll")
    public List<ResponseFindAllCategoryDto> findAll(){
        List<ResponseFindAllCategoryDto> all = categoryService.getCategoryList();

        return all;
    }
}
