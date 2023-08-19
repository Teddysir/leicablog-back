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

    @Transactional(rollbackFor = Exception.class)
    public List<ResponseFindAllCategoryDto> getCategoryList() {
        List<ResponseFindAllCategoryDto> results = categoryRepository.findAll().stream().map(ResponseFindAllCategoryDto::of).collect(Collectors.toList());
        return results;
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
