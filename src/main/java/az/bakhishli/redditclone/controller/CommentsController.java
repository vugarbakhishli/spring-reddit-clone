package az.bakhishli.redditclone.controller;

import az.bakhishli.redditclone.dto.comment.CommentsDto;
import az.bakhishli.redditclone.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentsDto commentsDto){
        commentService.createComment(commentsDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<Set<CommentsDto>> getAllCommentsForPost(@PathVariable Long postId){
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<Set<CommentsDto>> getAllCommentsForUser(@PathVariable String userName){
        return ResponseEntity.status(OK).body(commentService.getAllCommentsForUser(userName));
    }

}
