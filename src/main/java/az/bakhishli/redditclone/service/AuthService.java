package az.bakhishli.redditclone.service;

import az.bakhishli.redditclone.domain.User;
import az.bakhishli.redditclone.dto.RefreshTokenRequest;
import az.bakhishli.redditclone.dto.auth.AuthenticationResponseDto;
import az.bakhishli.redditclone.dto.auth.LoginRequestDto;
import az.bakhishli.redditclone.dto.auth.RegisterRequestDto;


public interface AuthService {

    void signup(RegisterRequestDto registerRequestDto);

    void verifyAccount(String token);

    AuthenticationResponseDto login(LoginRequestDto loginRequestDto);

    User getCurrentUser();

    AuthenticationResponseDto refreshToken(RefreshTokenRequest refreshTokenRequest);
}
