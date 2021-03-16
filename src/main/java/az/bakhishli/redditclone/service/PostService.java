package az.bakhishli.redditclone.service;


import az.bakhishli.redditclone.dto.post.PostRequestDto;
import az.bakhishli.redditclone.dto.post.PostResponseDto;

import java.util.Set;

public interface PostService{

    PostResponseDto createPost(PostRequestDto postRequestDto);

    Set<PostResponseDto> getAllPosts();

    PostResponseDto getPostById(Long postId);

    Set<PostResponseDto> getPostsBySubredditId(Long subredditId);

    Set<PostResponseDto> getPostsByUsername(String username);
}
