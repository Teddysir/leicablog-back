package leica.blog.controller;

import leica.blog.dto.PostDto;
import leica.blog.entity.Post;
import leica.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/admin/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return ResponseEntity.ok("게시물이 생성되었습니다.");
    }

    @GetMapping("/post")
    public List<Post> findAllPost(){
        return postService.findAllPost();
    }
}
