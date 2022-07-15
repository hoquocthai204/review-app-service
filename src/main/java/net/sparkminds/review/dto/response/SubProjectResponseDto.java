package net.sparkminds.review.dto.response;

import lombok.Builder;
import lombok.Data;
import net.sparkminds.review.entity.enumeration.CapacityStatus;
import net.sparkminds.review.entity.enumeration.EmploymentMode;

@Data
@Builder
public class SubProjectResponseDto {

    private String name;

    private EmploymentMode employmentMode;

    private CapacityStatus capacity;

    private String durationInMonths;

    private String startYear;

    private String role;

    private Integer teamSize;

    private String linkRepo;
    private String linkLive;
}
