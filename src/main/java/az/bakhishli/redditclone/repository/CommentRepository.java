package az.bakhishli.redditclone.repository;

import az.bakhishli.redditclone.domain.Comment;
import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Set<Comment> findByPost(Post post);

    Set<Comment> findAllByUser(User user);
}
