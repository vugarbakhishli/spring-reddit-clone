package az.bakhishli.redditclone.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Subreddit.TABLE_NAME)
public class Subreddit {
    public static final String TABLE_NAME = "subreddits";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @NotBlank
    @NotNull
    @NotEmpty
    private String description;

    @Builder.Default
    @OneToMany(fetch = LAZY, mappedBy = "subreddit")
    private Set<Post> posts = new HashSet<>();

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    private User user;
}
