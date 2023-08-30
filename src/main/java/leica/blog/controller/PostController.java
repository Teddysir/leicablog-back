package leica.blog.controller;

import leica.blog.dto.PostDto;
import leica.blog.dto.PostUpdateDto;
import leica.blog.dto.ResponseAllPostByCategory;
import leica.blog.service.PostService;
import leica.blog.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final SearchService searchService;


    // 전체 게시물 GET (썸네일 이미지 추가해야함!)
    @GetMapping("/post")
    public Map<Integer, List<PostDto>> findAllPost(){
        return postService.findAllPost();
    }

    // 게시물 생성
    @PostMapping("/admin/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
        return ResponseEntity.ok("게시물이 생성되었습니다.");
    }

    // 카테고리별 게시물 조회
    @GetMapping("/{parentCategory}")
    public Map<Integer, List<ResponseAllPostByCategory>> findPostInParentCategory(@PathVariable String parentCategory){
        return postService.findAllPost(parentCategory);
    }
    @GetMapping("/{parentCategory}/{childCategory}")
    public Map<Integer, List<PostDto>> findPostInChildCategory(@PathVariable String parentCategory, @PathVariable String childCategory){
        return postService.findAllPost(parentCategory, childCategory);
    }


    // 게시물 수정

    @PutMapping("/admin/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto dto){
        postService.updatePost(id,dto);
        return ResponseEntity.ok("게시물이 수정되었습니다.");
    }

    // 게시물 삭제
    @DeleteMapping("/admin/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("게시물이 삭제되었습니다.");
    }

    // 게시물 검색(완성)
    @GetMapping("/api/post/search")
    public Map<Integer, List<PostDto>> searchPost(@RequestParam(value = "keyword")String keyword){
        Map<Integer, List<PostDto>> posts = searchService.SearchPost(keyword);

        return posts;
    }
}
