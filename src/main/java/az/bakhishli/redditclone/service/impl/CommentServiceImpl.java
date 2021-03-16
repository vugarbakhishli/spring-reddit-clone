package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.domain.Comment;
import az.bakhishli.redditclone.domain.NotificationEmail;
import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.comment.CommentsDto;
import az.bakhishli.redditclone.exception.NotFoundException;
import az.bakhishli.redditclone.exception.PostNotFoundException;
import az.bakhishli.redditclone.mapper.CommentMapper;
import az.bakhishli.redditclone.repository.CommentRepository;
import az.bakhishli.redditclone.repository.PostRepository;
import az.bakhishli.redditclone.repository.UserRepository;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.CommentService;
import az.bakhishli.redditclone.service.MailContentBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    @Override
    @Transactional
    public void createComment(CommentsDto commentsDto) {

            Post post = postRepository.findById(commentsDto.getPostId())
                    .orElseThrow(
                            () -> new PostNotFoundException(commentsDto.getPostId().toString()));
            User user = userRepository.findByUsername(commentsDto.getUserName())
                    .orElseThrow(
                            () -> new NotFoundException("Username not found: " + commentsDto.getUserName()));

            Comment comment = commentMapper.map(commentsDto, post, user);
            commentRepository.save(comment);

            String message = mailContentBuilder.build(post.getUser().getUsername()
                    + " posted a comment on your post. " + POST_URL);
            sendCommentNotification(message, post.getUser());

    }

    @Override
    @Transactional(readOnly = true)
    public Set<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(()
                -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toSet());
    }

    private void sendCommentNotification(String message, User user){
        mailService.sendMail(new NotificationEmail(user.getUsername()
                + " Commented on your post",
                user.getEmail(), message));
    }




}
