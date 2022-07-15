package net.sparkminds.review.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponseDto {
    private String name;
    private String emailAddress;
    private String githubUser;
}
