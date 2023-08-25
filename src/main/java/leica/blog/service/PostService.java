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
    public List<PostDto> findAllPost() {
        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream().map(post ->
        {
            Category parent = post.getCategory().getParent();
            Category category = post.getCategory();

            return PostDto.builder()
                    .parentCategory(parent.getName())
                    .category(category.getName())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .createdAt(post.getCreate_at())
                    .modifiedAt(post.getModified_at())
                    .build();
        }).collect(Collectors.toList());

        if (collect.isEmpty()) {
            return new ArrayList<>(); // 빈 리스트 반환
        }
        return collect;
    }

    @Transactional
    public Map<Integer, List<ResponseAllPostByCategory>> findAllPost(String categoryName) {
        Map<Integer, List<ResponseAllPostByCategory>> response = new HashMap<>();
        List<Post> byCategoryName = postRepository.findByCategoryName(categoryName);
        int postCount = byCategoryName.size();
        List<ResponseAllPostByCategory> collect = byCategoryName.stream().map(post -> ResponseAllPostByCategory.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .create_at(post.getCreate_at())
                .build()).collect(Collectors.toList());

        response.put(postCount, collect);
        return response;
    }

    @Transactional
    public Long findOnePost(Long id) { // id값만 리턴해주면 되려나?
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );
        return id;
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
