package net.sparkminds.review.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponseDto {
    
    private String emailAddress;

    private String name;

    private String githubUser;

    private List<SubProjectResponseDto> pastProjects;
}
