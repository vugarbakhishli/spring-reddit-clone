package az.bakhishli.redditclone.service.impl;

import az.bakhishli.redditclone.domain.NotificationEmail;
import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.domain.VerificationToken;
import az.bakhishli.redditclone.dto.RefreshTokenRequest;
import az.bakhishli.redditclone.dto.auth.AuthenticationResponseDto;
import az.bakhishli.redditclone.dto.auth.LoginRequestDto;
import az.bakhishli.redditclone.dto.auth.RegisterRequestDto;
import az.bakhishli.redditclone.exception.SpringRedditException;
import az.bakhishli.redditclone.repository.UserRepository;
import az.bakhishli.redditclone.repository.VerificationTokenRepository;
import az.bakhishli.redditclone.security.JwtProvider;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public void signup(RegisterRequestDto registerRequestDto) {
        User user = User.builder()
                .username(registerRequestDto.getUsername())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .createdDate(Instant.now())
                .enabled(false)
                .build();

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(
                NotificationEmail.builder()
                .subject("Please Activate your account")
                .recipient(user.getEmail())
                .body("Thank you for signing up to Spring Reddit Clone, " +
                        "please click on the below url to activate your account: " +
                        "http://localhost:8080/api/auth/accountVerification/" + token)
                .build()
        );
    }

    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .build();

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    @Override
    @Transactional
    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()-> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Override
    public AuthenticationResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);

        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequestDto.getUsername())
                .build();
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(()
                        -> new UsernameNotFoundException("User name not found - "
                        + principal.getUsername()));
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponseDto refreshToken(RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());

        return AuthenticationResponseDto.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }



}
