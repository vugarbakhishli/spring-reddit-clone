package az.bakhishli.redditclone.mapper;

import az.bakhishli.redditclone.domain.Comment;
import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.comment.CommentsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentsDto mapToDto(Comment comment);
}
