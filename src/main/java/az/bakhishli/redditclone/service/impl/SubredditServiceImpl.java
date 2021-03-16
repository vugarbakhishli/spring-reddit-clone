package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.domain.Subreddit;
import az.bakhishli.redditclone.dto.subreddit.SubredditDto;
import az.bakhishli.redditclone.exception.BadRequestException;
import az.bakhishli.redditclone.exception.NotFoundException;
import az.bakhishli.redditclone.mapper.SubredditMapper;
import az.bakhishli.redditclone.repository.SubredditRepository;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final AuthService authService;

    @Transactional
    @Override
    public void createSubreddit(SubredditDto subredditDto) {
        try {
            subredditRepository.save(
                    subredditMapper.mapDtoToSubreddit(subredditDto, authService.getCurrentUser())
            );
        }catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Set<SubredditDto> getAllSubreddits() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    @Override
    public SubredditDto getSubredditById(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(()-> new NotFoundException("No Subreddit found with id: " + subredditId));
       return subredditMapper.mapSubredditToDto(subreddit);
    }


}
