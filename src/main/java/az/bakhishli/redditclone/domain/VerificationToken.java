package az.bakhishli.redditclone.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
//import static javax.persistence.FetchType.EAGER;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = VerificationToken.TABLE_NAME)
public class VerificationToken {

    public static final String TABLE_NAME = "verification_tokens";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(fetch = LAZY)
    private User user;

    @Column(name = "expiry_date")
    private Instant expiryDate;
}
