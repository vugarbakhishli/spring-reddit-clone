package az.bakhishli.redditclone.controller;

import az.bakhishli.redditclone.dto.post.PostRequestDto;
import az.bakhishli.redditclone.dto.post.PostResponseDto;
import az.bakhishli.redditclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto postRequestDto){
        return ResponseEntity.status(CREATED)
                .body(postService.createPost(postRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id){
        return status(OK).body(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<Set<PostResponseDto>> getAllPosts(){
        return status(OK).body(postService.getAllPosts());
    }

    @GetMapping("/by-subreddit/{subredditId}")
    public ResponseEntity<Set<PostResponseDto>> getPostsBySubredditId(@PathVariable Long subredditId){
        return status(OK).body(postService.getPostsBySubredditId(subredditId));
    }

    @GetMapping("/by-user/{name}")
    public ResponseEntity<Set<PostResponseDto>> getPostsByUsername(@PathVariable String name){
        return status(OK).body(postService.getPostsByUsername(name));
    }



}
