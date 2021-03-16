package az.bakhishli.redditclone.service;

import az.bakhishli.redditclone.dto.subreddit.SubredditDto;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface SubredditService {

    void createSubreddit(SubredditDto subredditDto);

    Set<SubredditDto> getAllSubreddits();

    SubredditDto getSubredditById(Long subredditId);
}
