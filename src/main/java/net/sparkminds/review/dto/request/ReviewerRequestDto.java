package net.sparkminds.review.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewerRequestDto {

    @NotBlank(message = "Reviewer name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    
    private String picture;
}
