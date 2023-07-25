package leica.blog.service;

import leica.blog.dto.PostDto;
import leica.blog.dto.PostUpdateDto;
import leica.blog.entity.Post;
import leica.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostDto dto) {
        return postRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public List<Post> findAllPost() {
        return postRepository.findAllByOrderById();
    }


}
