package leica.blog.service;

import leica.blog.dto.ResponseFindAllCategoryDto;
import leica.blog.entity.Category;
import leica.blog.error.DuplicateCategoryException;
import leica.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(rollbackFor = Exception.class)
    public ResponseFindAllCategoryDto getCategoryList(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        ResponseFindAllCategoryDto categoryDto = ResponseFindAllCategoryDto.of(category);

        return categoryDto;
    }
    public Category createCategory(String name, String parentName) throws DuplicateCategoryException {
        Category newCategory;

        if (parentName == null){
            newCategory = new Category(name, null);
        }else{
            Category category = categoryRepository.findByName(parentName);
            findDuplicateCategoryName(category, name);
            newCategory = new Category(name, category);
        }

        Category saved = categoryRepository.save(newCategory);
        return saved;

    }

    private void findDuplicateCategoryName(Category category, String name) throws DuplicateCategoryException {
        List<Category> children = category.getChildren();
        for (Category child : children) {
            if(child.getName().equals(name)){
                throw new DuplicateCategoryException("중복된 카테고리 명이 있습니다.");
            }
        }
    }
}
