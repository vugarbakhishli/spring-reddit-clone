package az.bakhishli.redditclone.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private Long id;

    private Long postId;

    private Instant createdDate;

    @NotBlank
    @NotNull
    @NotEmpty
    private String text;

    @NotBlank
    @NotNull
    @NotEmpty
    private String userName;
}
