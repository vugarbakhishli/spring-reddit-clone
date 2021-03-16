package az.bakhishli.redditclone.service;

import az.bakhishli.redditclone.dto.comment.CommentsDto;

import java.util.Set;

public interface CommentService {

    void createComment(CommentsDto commentsDto);

    Set<CommentsDto> getAllCommentsForPost(Long postId);

    Set<CommentsDto> getAllCommentsForUser(String userName);
}
