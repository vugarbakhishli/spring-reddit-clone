package az.bakhishli.redditclone.repository;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByIdDesc(Post post, User currentUser);
}
