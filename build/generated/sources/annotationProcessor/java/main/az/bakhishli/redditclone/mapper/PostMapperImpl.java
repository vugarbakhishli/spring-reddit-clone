package az.bakhishli.redditclone.mapper;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.post.PostRequestDto;
import az.bakhishli.redditclone.dto.post.PostResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-14T18:52:29+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequestDto postRequestDto, Subreddit subreddit, User user) {
        if ( postRequestDto == null && subreddit == null && user == null ) {
            return null;
        }

        Post post = new Post();

        if ( postRequestDto != null ) {
            post.setDescription( postRequestDto.getDescription() );
            post.setTitle( postRequestDto.getTitle() );
            post.setUrl( postRequestDto.getUrl() );
        }
        if ( subreddit != null ) {
            post.setSubreddit( subredditToSubreddit( subreddit ) );
        }
        if ( user != null ) {
            post.setUser( userToUser( user ) );
        }
        post.setCreatedDate( java.time.Instant.now() );
        post.setVoteCount( 0 );

        return post;
    }

    @Override
    public PostResponseDto mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponseDto postResponseDto = new PostResponseDto();

        postResponseDto.setId( post.getId() );
        postResponseDto.setSubredditName( postSubredditName( post ) );
        postResponseDto.setUserName( postUserUsername( post ) );
        postResponseDto.setTitle( post.getTitle() );
        postResponseDto.setUrl( post.getUrl() );
        postResponseDto.setDescription( post.getDescription() );
        postResponseDto.setVoteCount( post.getVoteCount() );

        postResponseDto.setCommentCount( commentCount(post) );
        postResponseDto.setDuration( getDuration(post) );

        return postResponseDto;
    }

    protected Subreddit subredditToSubreddit(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        Subreddit subreddit1 = new Subreddit();

        subreddit1.setId( subreddit.getId() );

        return subreddit1;
    }

    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }

    private String postSubredditName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddit subreddit = post.getSubreddit();
        if ( subreddit == null ) {
            return null;
        }
        String name = subreddit.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
