package az.bakhishli.redditclone.mapper;

import az.bakhishli.redditclone.domain.Comment;
import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.comment.CommentsDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-14T18:52:29+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDto commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentsDto != null ) {
            comment.setText( commentsDto.getText() );
        }
        if ( post != null ) {
            comment.setPost( post );
        }
        if ( user != null ) {
            comment.setUser( user );
        }
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setPostId( comment.getPost().getId() );
        commentsDto.setUserName( comment.getUser().getUsername() );

        return commentsDto;
    }
}
