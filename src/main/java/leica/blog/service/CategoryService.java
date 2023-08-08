package leica.blog.service;

import leica.blog.dto.ResponseFindAllCategoryDto;
import leica.blog.entity.Category;
import leica.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<ResponseFindAllCategoryDto> findAll(){
        List<Category> categoryList = categoryRepository.findAllOrderByParentIdAscNullsFirstCategoryIdAsc();

        List<ResponseFindAllCategoryDto> collect = categoryList.stream().map(category -> {
            String parentName = category.getParent() != null ? category.getParent().getName() : null;

            return ResponseFindAllCategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .parentName(parentName)
                    .build();
        }).collect(Collectors.toList());


        return collect;
    }
    public Category createCategory(String name, String parentName){
        Category newCategory;
        if (parentName != null){
            Category category = categoryRepository.findByName(parentName);
            newCategory = new Category(name, category);
        }else{
            newCategory = new Category(name, null);

        }
        Category saved = categoryRepository.save(newCategory);
        return saved;

    }
}
