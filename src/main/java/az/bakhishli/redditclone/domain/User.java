package az.bakhishli.redditclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Size(min = 7)
    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotBlank
    @NotNull
    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    private String email;

    private Instant createdDate;

    @Column(name = "enabled")
    private boolean enabled;


}
