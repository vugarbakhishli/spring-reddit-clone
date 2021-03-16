package az.bakhishli.redditclone.service;

import az.bakhishli.redditclone.domain.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);

}
