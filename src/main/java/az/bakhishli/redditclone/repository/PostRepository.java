package az.bakhishli.redditclone.repository;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findAllBySubreddit(Subreddit subreddit);

    Set<Post> findByUser(User user);
}
