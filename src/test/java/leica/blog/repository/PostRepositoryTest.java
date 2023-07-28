package leica.blog.repository;

import leica.blog.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostRepositoryTest {


    @Autowired
    private PostRepository postRepository;

    @Test
    public void 제목검색(){
        Post post1 = new Post( 1L, "title1", "This is my first post", "abc");
        Post post2 = new Post(2L, "title2", "This is my second post", "bcd");

        Post save1 = postRepository.save(post1);
        Post save2 = postRepository.save(post2);


        List<Post> byTitleContainingOrContentContaining = postRepository.findByTitleContainingOrContentContaining("title", "title");

        Assertions.assertThat(byTitleContainingOrContentContaining).hasSize(2);


    }

    @Test
    public void 게시물내용으로검색(){
        Post post1 = new Post( 1L, "title1", "This is my first post", "abc");
        Post post2 = new Post(2L, "title2", "This is my second post", "bcd");

        Post save1 = postRepository.save(post1);
        Post save2 = postRepository.save(post2);


        List<Post> byTitleContainingOrContentContaining = postRepository.findByTitleContainingOrContentContaining("my", "my");

        Assertions.assertThat(byTitleContainingOrContentContaining).hasSize(2);


    }


}