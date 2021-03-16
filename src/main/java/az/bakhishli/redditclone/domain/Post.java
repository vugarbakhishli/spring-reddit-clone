package az.bakhishli.redditclone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = Post.TABLE_NAME)
public class Post {
    public static final String TABLE_NAME = "posts";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "title")
    private String title;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "url")
    private String url;

    @Lob
    private String description;

    @Builder.Default
    @Column(name = "vote_count")
    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subreddit_id", referencedColumnName = "id")
    private Subreddit subreddit;
}
