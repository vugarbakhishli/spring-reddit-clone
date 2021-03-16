package az.bakhishli.redditclone.controller;

import az.bakhishli.redditclone.dto.RefreshTokenRequest;
import az.bakhishli.redditclone.dto.auth.AuthenticationResponseDto;
import az.bakhishli.redditclone.dto.auth.LoginRequestDto;
import az.bakhishli.redditclone.dto.auth.RegisterRequestDto;
import az.bakhishli.redditclone.service.AuthService;
import az.bakhishli.redditclone.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestDto registerRequestDto){
        authService.signup(registerRequestDto);
        return new ResponseEntity<>("User Registration successful!", OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully!", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponseDto refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }



}
