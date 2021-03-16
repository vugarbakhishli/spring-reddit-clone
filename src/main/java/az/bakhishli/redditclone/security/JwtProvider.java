package az.bakhishli.redditclone.security;

import az.bakhishli.redditclone.exception.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;



@Service
public class JwtProvider {

    @Value("${security.jwt.token.expire-time}")
    private Long jwtExpirationInMillis;

    @Value("${security.jwt.token.secret-key}")
    private String secret;

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getSigningKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


    private String getMd5Secret() {
        return DigestUtils.md5DigestAsHex(secret.getBytes());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(getMd5Secret().getBytes());
    }

    public boolean validateToken(String jwt){
        Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build().
                parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String generateTokenWithUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getSigningKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }


}
