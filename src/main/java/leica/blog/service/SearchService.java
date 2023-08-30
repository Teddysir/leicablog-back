package leica.blog.service;

import leica.blog.dto.PostDto;
import leica.blog.entity.Post;
import leica.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostRepository postRepository;

    @Transactional
    public Map<Integer, List<PostDto>> SearchPost (String keyword){
        Map<Integer, List<PostDto>> response = new HashMap<>();
        List<Post> postList = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        int listSize = postList.size();

        List<PostDto> collect = postList.stream().map(post ->
                    PostDto.builder()
                            .title(post.getTitle())
                            .content(post.getContent())
                            .category(post.getCategory().getName())
                            .parentCategoryName(post.getCategory().getParent().getName())
                            .createdAt(post.getCreate_at())
                            .modifiedAt(post.getModified_at())
                            .build())
                .collect(Collectors.toList());

        response.put(listSize, collect);
        return response;
    }
}
