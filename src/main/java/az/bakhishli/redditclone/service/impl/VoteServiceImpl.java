package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.Vote;
import az.bakhishli.redditclone.dto.vote.VoteDto;
import az.bakhishli.redditclone.exception.AlreadyVotedPostException;
import az.bakhishli.redditclone.exception.PostNotFoundException;
import az.bakhishli.redditclone.exception.SpringRedditException;
import az.bakhishli.redditclone.repository.PostRepository;
import az.bakhishli.redditclone.repository.VoteRepository;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static az.bakhishli.redditclone.domain.enums.VoteType.UPVOTE;

@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(
                        () -> new PostNotFoundException("Post Not Found with ID - "
                                + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser  = voteRepository.findTopByPostAndUserOrderByIdDesc
                (post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent()) {
            throw new AlreadyVotedPostException("You have already " + voteDto.getVoteType()
                    + "'d for this post");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);

    }

    private Vote mapToVote(VoteDto voteDto, Post post){
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }


}
