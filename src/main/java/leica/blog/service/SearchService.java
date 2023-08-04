package leica.blog.service;

import leica.blog.dto.PostDto;
import leica.blog.entity.Post;
import leica.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PostRepository postRepository;

    @Transactional
    public List<PostDto> SearchPost (String keyword){
        List<Post> postList = postRepository.findByTitleContainingOrContentContaining(keyword, keyword);

        List<PostDto> collect = postList.stream().map(post ->
                    PostDto.builder()
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build())
                .collect(Collectors.toList());

        return collect;
    }
}
