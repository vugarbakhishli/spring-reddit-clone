package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.domain.Post;
import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.post.PostRequestDto;
import az.bakhishli.redditclone.dto.post.PostResponseDto;
import az.bakhishli.redditclone.exception.NotFoundException;
import az.bakhishli.redditclone.exception.PostNotFoundException;
import az.bakhishli.redditclone.mapper.PostMapper;
import az.bakhishli.redditclone.repository.PostRepository;
import az.bakhishli.redditclone.repository.SubredditRepository;
import az.bakhishli.redditclone.repository.UserRepository;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
       Subreddit subreddit = subredditRepository.findByName(postRequestDto.getSubredditName())
                .orElseThrow(()-> new NotFoundException(postRequestDto.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        Post map = postMapper.map(postRequestDto, subreddit, currentUser);
        Post save = postRepository.save(map);

        return  postMapper.mapToDto(save);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException(postId.toString()));
        return postMapper.mapToDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PostResponseDto> getPostsBySubredditId(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(()-> new NotFoundException(subredditId.toString()));
        Set<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PostResponseDto> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new NotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toSet());
    }
}
