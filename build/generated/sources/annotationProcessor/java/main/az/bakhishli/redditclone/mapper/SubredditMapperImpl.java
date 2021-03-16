package az.bakhishli.redditclone.mapper;

import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.subreddit.SubredditDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-03-14T18:52:29+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-6.7.jar, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDto subredditDto = new SubredditDto();

        subredditDto.setId( subreddit.getId() );
        subredditDto.setName( subreddit.getName() );
        subredditDto.setDescription( subreddit.getDescription() );

        subredditDto.setNumberOfPosts( mapPosts(subreddit.getPosts()) );

        return subredditDto;
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subredditDto, User user) {
        if ( subredditDto == null && user == null ) {
            return null;
        }

        Subreddit subreddit = new Subreddit();

        if ( subredditDto != null ) {
            subreddit.setName( subredditDto.getName() );
            subreddit.setDescription( subredditDto.getDescription() );
        }
        if ( user != null ) {
            subreddit.setUser( userToUser( user ) );
        }
        subreddit.setCreatedDate( java.time.Instant.now() );

        return subreddit;
    }

    protected User userToUser(User user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setId( user.getId() );

        return user1;
    }
}
