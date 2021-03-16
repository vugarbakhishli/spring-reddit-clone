package az.bakhishli.redditclone.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
}
