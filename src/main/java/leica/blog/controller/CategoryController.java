package leica.blog.controller;

import leica.blog.dto.RequestCreateCategoryDto;
import leica.blog.dto.ResponseFindAllCategoryDto;
import leica.blog.error.DuplicateCategoryException;
import leica.blog.repository.CategoryRepository;
import leica.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody RequestCreateCategoryDto requestCreateCategoryDto){

        try{
            String name = requestCreateCategoryDto.getName();
            String parentName = requestCreateCategoryDto.getParentName();
            categoryService.createCategory(name, parentName);

        }catch (DuplicateCategoryException e) {
            // 중복된 카테고리 에러 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 카테고리 이름입니다.");
        }
        return ResponseEntity.ok("created!");
    }

    @GetMapping("/{categoryName}")
    public ResponseFindAllCategoryDto findAll(@PathVariable String categoryName){
//        List<ResponseFindAllCategoryDto> all = categoryService.getCategoryList(categoryName);
        ResponseFindAllCategoryDto categoryList = categoryService.getCategoryList(categoryName);
        return categoryList;
    }
}
