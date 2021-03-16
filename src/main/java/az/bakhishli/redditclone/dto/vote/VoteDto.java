package az.bakhishli.redditclone.dto.vote;

import az.bakhishli.redditclone.domain.enums.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private VoteType voteType;

    private Long postId;
}
