package leica.blog.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import leica.blog.dto.PostDto;
import leica.blog.dto.PostUpdateDto;
import leica.blog.dto.ResponsePostResult;
import leica.blog.entity.Category;
import leica.blog.entity.Post;
import leica.blog.repository.CategoryRepository;
import leica.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createPost(PostDto dto) {
        Category parentCategory = categoryRepository.findByName(dto.getParentCategoryName());
        Category category = findChildCategoryByName(parentCategory, dto.getCategoryName());

        if (category == null){
            throw new NoSuchElementException("존재하지 않는 카테고리입니다.");
        }
        Post entity = dto.toEntity(category);

        return postRepository.save(entity).getId();
    }

    private Category findChildCategoryByName(Category parentCategory, String name) {
        List<Category> childCategories = parentCategory.getChildren();
        for (Category child : childCategories) {
            if (child.getName().equals(name)) {
                return child; // 일치하는 자식 카테고리를 반환
            }
        }
        return null; // 일치하는 자식 카테고리가 없을 경우 null 반환
    }

    @Transactional
    public String findAllPost() throws JsonProcessingException {
        List<Post> postList = postRepository.findAll();
        int listSize = postList.size();

        List<PostDto> postDtoList = postList.stream().map(post -> PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory().getName())
                .parentCategoryName(post.getCategory().getParent().getName())
                .createdAt(post.getCreate_at())
                .modifiedAt(post.getModified_at())
                .build()).collect(Collectors.toList());

        ResponsePostResult responsePostResult = new ResponsePostResult(listSize, postDtoList);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(responsePostResult);

        return value;
    }

    // 카테고리별 게시물 반환
    @Transactional
    public String findAllPost(String parentCategoryName) throws JsonProcessingException {
        Category parentCategory = categoryRepository.findByName(parentCategoryName);

        List<String> childCategoryNames = parentCategory.getChildren().stream()
                .map(Category::getName)
                .collect(Collectors.toList());

        List<Post> postList = new ArrayList<>();
        findAllPostsInCategories(parentCategoryName, childCategoryNames, postList);

        int postCount = postList.size();

        List<PostDto> postDtoList = postList.stream().map(post -> PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory().getName())
                .parentCategoryName(post.getCategory().getParent().getName())
                .createdAt(post.getCreate_at())
                .modifiedAt(post.getModified_at())
                .build()).collect(Collectors.toList());

        ResponsePostResult responsePostResult = new ResponsePostResult(postCount, postDtoList);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(responsePostResult);

        return value;
    }

    private void findAllPostsInCategories(String parentCategoryName,List<String> categoryNames, List<Post> result) {
        for (String categoryName : categoryNames) {
            List<Post> postsInCategory = postRepository.findByCategoryName(categoryName);
            for (Post post : postsInCategory){
                if (post.getParentCategoryName().equals(parentCategoryName)){
                    result.add(post);
                }
            }
        }
    }




    public String findAllPost(String parentCategory, String childCategory) throws JsonProcessingException {
        Category category = categoryRepository.findByName(childCategory);
        List<Post> postList = postRepository.findByCategoryName(category.getName());
        int postCount = postList.size();

        List<PostDto> postDtoList = postList.stream().map(post -> PostDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory().getName())
                .parentCategoryName(post.getCategory().getParent().getName())
                .createdAt(post.getCreate_at())
                .modifiedAt(post.getModified_at())
                .build()).collect(Collectors.toList());

        ResponsePostResult responsePostResult = new ResponsePostResult(postCount, postDtoList);
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(responsePostResult);

        return value;
    }


    @Transactional
    public Long updatePost(Long id, PostUpdateDto dto) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        post.update(dto.getTitle(),dto.getContent());
        return id;
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        postRepository.delete(post);
    }


}
