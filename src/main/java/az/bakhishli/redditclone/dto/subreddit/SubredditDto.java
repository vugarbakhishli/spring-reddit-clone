package az.bakhishli.redditclone.dto.subreddit;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
