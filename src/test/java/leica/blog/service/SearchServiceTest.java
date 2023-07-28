package leica.blog.service;

import leica.blog.dto.PostDto;
import leica.blog.entity.Post;
import leica.blog.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시물검색서비스_테스트(){
        Post post1 = new Post( 1L, "title1", "This is my first post", "abc");
        Post post2 = new Post(2L, "title2", "This is my second post", "bcd");

        postRepository.save(post1);
        postRepository.save(post2);

        List<PostDto> searchPost = searchService.SearchPost("MY");

        Assertions.assertThat(searchPost).hasSize(2);
    }

}


