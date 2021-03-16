package az.bakhishli.redditclone.mapper;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.post.PostRequestDto;
import az.bakhishli.redditclone.dto.post.PostResponseDto;
import az.bakhishli.redditclone.repository.CommentRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subreddit.id", source = "subreddit.id")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "description", source = "postRequestDto.description")
    @Mapping(target = "user.id", source = "user.id")
    public abstract Post map(PostRequestDto postRequestDto, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponseDto mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.Companion.using(post.getCreatedDate().toEpochMilli());
    }


}
