package net.sparkminds.review.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.review.entity.enumeration.Capacity;
import net.sparkminds.review.entity.enumeration.EmploymentMode;

@Data
@Builder
public class ProjectRequestDto {

    @NotBlank(message = "Project name is required")
    private String name;

    @NotBlank(message = "Emplayment mode is required")
    private EmploymentMode employmentMode;

    @NotBlank(message = "Capacity is required")
    private Capacity capacity;

    @NotBlank(message = "Duration in months")
    private String durationInMonths;

    @NotBlank(message = "Start year is required")
    private String startYear;

    @NotBlank(message = "Role is required")
    private String role;

    @NotBlank(message = "Team size is required")
    private Number teamSize;

    private String linkRepo;
    private String linkLive;

    @NotBlank(message = "Profile id is required")
    private Long profileId;
}
