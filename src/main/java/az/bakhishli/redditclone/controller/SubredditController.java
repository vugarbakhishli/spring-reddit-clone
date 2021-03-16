package az.bakhishli.redditclone.controller;

import az.bakhishli.redditclone.dto.subreddit.SubredditDto;
import az.bakhishli.redditclone.service.SubredditService;
import az.bakhishli.redditclone.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import java.util.Set;

@Controller
@RequestMapping("/api/subreddits")
@RequiredArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody SubredditDto subredditDto){
        subredditService.createSubreddit(subredditDto);
        return ResponseEntity.status(CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<Set<SubredditDto>> getAllSubreddits(){
        return ResponseEntity.status(OK)
                .body(subredditService.getAllSubreddits());
    }

    @GetMapping("/{subredditId}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable Long subredditId){
        return ResponseEntity.status(OK).body(subredditService.getSubredditById(subredditId));
    }

}
