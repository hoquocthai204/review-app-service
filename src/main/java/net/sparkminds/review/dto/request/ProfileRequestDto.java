package net.sparkminds.review.dto.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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

    @Valid
    @NotEmpty(message = "Past projects is required")
//    @NotBlank(message = "Past projects is required")
    private List<ChildProjectRequestDto> pastProjects;
}
