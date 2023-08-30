package leica.blog.service;

import leica.blog.dto.PostDto;
import leica.blog.dto.PostUpdateDto;
import leica.blog.dto.ResponseAllPostByCategory;
import leica.blog.entity.Category;
import leica.blog.entity.Post;
import leica.blog.repository.CategoryRepository;
import leica.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long createPost(PostDto dto) {
        Category byName = categoryRepository.findByName(dto.getCategoryName());
        Post entity = dto.toEntity(byName);

        return postRepository.save(entity).getId();
    }

    @Transactional
    public Map<Integer, List<PostDto>> findAllPost() {
        List<Post> all = postRepository.findAll();
        int listSize = all.size();
        if (listSize == 0){
            return new HashMap<>();
        }else{
            Map<Integer, List<PostDto>> response = new HashMap<>();
            List<PostDto> collect = all.stream().map(post ->
            {
                Category parent = post.getCategory().getParent();
                Category category = post.getCategory();

                return PostDto.builder()
                        .parentCategoryName(parent.getName())
                        .category(category.getName())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .createdAt(post.getCreate_at())
                        .modifiedAt(post.getModified_at())
                        .build();
            }).collect(Collectors.toList());
            response.put(listSize, collect);
            return response;
        }
    }

    // 카테고리별 게시물 반환
    @Transactional
    public Map<Integer, List<ResponseAllPostByCategory>> findAllPost(String categoryName) {
        Map<Integer, List<ResponseAllPostByCategory>> response = new HashMap<>();
        Category category = categoryRepository.findByName(categoryName);

        List<Post> postsInCategoryAndChildren = new ArrayList<>();
        findAllPostsInCategoryAndChildrenRecursively(category, postsInCategoryAndChildren);

        int postCount = postsInCategoryAndChildren.size();

        List<ResponseAllPostByCategory> collect = postsInCategoryAndChildren.stream().map(post -> ResponseAllPostByCategory.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .create_at(post.getCreate_at())
                .build()).collect(Collectors.toList());

        response.put(postCount, collect);
        return response;
    }

    private void findAllPostsInCategoryAndChildrenRecursively(Category category, List<Post> result) {
        if (category != null) {
            for (Post post : postRepository.findByCategoryName(category.getName())) {
                result.add(post);
            }
            for (Category child : category.getChildren()) {
                findAllPostsInCategoryAndChildrenRecursively(child, result);
            }
        }
    }



    public Map<Integer, List<PostDto>> findAllPost(String parentCategory, String childCategory) {
        Category category = categoryRepository.findByName(childCategory);
        List<Post> postList = postRepository.findByCategoryName(category.getName());
        int postCount = postList.size();

        if (postCount == 0){
            return new HashMap<>();  // 빈 hashmap 반환
        }else{

            List<PostDto> postDtoList = postList.stream().map(post -> PostDto.builder()
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory().getName())
                    .parentCategoryName(post.getCategory().getParent().getName())
                    .createdAt(post.getCreate_at())
                    .modifiedAt(post.getModified_at())
                    .build()).collect(Collectors.toList());
            HashMap<Integer, List<PostDto>> hashMap = new HashMap<>();
            hashMap.put(postCount, postDtoList);
            return hashMap;
        }
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
