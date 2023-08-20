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
