package net.sparkminds.review.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileRequestDto {
    @NotBlank(message = "Email address is required")
    private String emailAddress;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Github user is required")
    private String githubUser;
}
