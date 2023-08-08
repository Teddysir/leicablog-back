package leica.blog.service;

import leica.blog.dto.ResponseFindAllCategoryDto;
import leica.blog.entity.Category;
import leica.blog.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(value = SpringRunner.class)
@Rollback(value = true)
public class CategoryServiceTest {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @Test
    public void 부모카테고리등록(){
        Category 한식 = new Category("한식", null);
        Category 중식 = new Category("중식", null);
        Category 일식 = new Category("일식", null);

        categoryRepository.save(한식);
        categoryRepository.save(중식);
        categoryRepository.save(일식);

        Assertions.assertThat(한식.getParent()).isEqualTo(null);
        Assertions.assertThat(중식.getParent()).isEqualTo(null);
        Assertions.assertThat(일식.getParent()).isEqualTo(null);

    }

    @Test
    public void 자식카테고리등록(){
        Category 한식 = new Category("한식", null);
        Category 중식 = new Category("중식", null);

        categoryRepository.save(한식);
        categoryRepository.save(중식);


        Category 비빔밥 = categoryService.createCategory("비빔밥", "한식");
        Category 탕수육 = categoryService.createCategory("탕수육", "중식");

        Assertions.assertThat(비빔밥.getParent().getName()).isEqualTo("한식");
        Assertions.assertThat(탕수육.getParent().getName()).isEqualTo("중식");

    }

    @Test
    public void 전체카테고리조회(){
        Category 한식 = categoryService.createCategory("한식", null);
        Category 중식 = categoryService.createCategory("중식", null);
        Category 일식 = categoryService.createCategory("일식", null);

        Category 비빔밥 = categoryService.createCategory("비빔밥", "한식");
        Category 탕수육 = categoryService.createCategory("탕수육", "중식");

        List<ResponseFindAllCategoryDto> all = categoryService.findAll();

        Assertions.assertThat(all).hasSize(5);
    }

}