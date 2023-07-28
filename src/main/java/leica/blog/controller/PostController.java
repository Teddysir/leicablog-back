package leica.blog.controller;

import leica.blog.dto.PostDto;
import leica.blog.dto.PostUpdateDto;
import leica.blog.entity.Post;
import leica.blog.service.PostService;
import leica.blog.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final SearchService searchService;

    @PostMapping("/admin/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return ResponseEntity.ok("게시물이 생성되었습니다.");
    }

    @GetMapping("/post")
    public List<Post> findAllPost(){
        return postService.findAllPost();
    }

    @PutMapping("/admin/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto dto){
        postService.updatePost(id,dto);
        return ResponseEntity.ok("게시물이 수정되었습니다.");
    }

    @DeleteMapping("/admin/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }

    @GetMapping("/api/post/search")
    public List<PostDto> searchPost(@RequestParam(value = "keyword")String keyword){
        List<PostDto> posts = searchService.SearchPost(keyword);

        return posts;
    }
}
