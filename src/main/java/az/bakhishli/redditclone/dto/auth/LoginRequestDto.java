package az.bakhishli.redditclone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank
    @NotNull
    @NotEmpty
    private String username;

    @NotBlank
    @NotNull
    @NotEmpty
    private String password;
}
