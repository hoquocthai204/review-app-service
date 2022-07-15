package net.sparkminds.review.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.review.entity.enumeration.CapacityStatus;
import net.sparkminds.review.entity.enumeration.EmploymentMode;

@Data
@Builder
public class SubProjectRequestDto {

    @NotBlank(message = "Project name is required")
    private String name;

    @NotNull(message = "Emplayment mode is required")
    private EmploymentMode employmentMode;

    @NotNull(message = "Capacity is required")
    private CapacityStatus capacity;

    @NotBlank(message = "Duration in months")
    private String durationInMonths;

    @NotBlank(message = "Start year is required")
    private String startYear;

    @NotBlank(message = "Role is required")
    private String role;

    @NotNull(message = "Team size is required")
    private Integer teamSize;

    private String linkRepo;
    private String linkLive;
}
