package az.bakhishli.redditclone.controller;

import az.bakhishli.redditclone.dto.vote.VoteDto;
import az.bakhishli.redditclone.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote (@RequestBody @Valid VoteDto voteDto){
        voteService.vote(voteDto);
        return new ResponseEntity<>(OK);
    }


}
