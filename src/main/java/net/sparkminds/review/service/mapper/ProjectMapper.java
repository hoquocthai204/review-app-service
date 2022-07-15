package net.sparkminds.review.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.review.dto.request.ChildProjectRequestDto;
import net.sparkminds.review.entity.Project;

@Service
public class ProjectMapper {
    public ChildProjectRequestDto toChildProjectDto(Project project) {
        return ChildProjectRequestDto
                .builder()
                .name(project.getName())
                .employmentMode(project.getEmploymentMode())
                .capacity(project.getCapacity())
                .durationInMonths(project.getDurationInMonths())
                .startYear(project.getStartYear())
                .role(project.getRole())
                .teamSize(project.getTeamSize())
                .linkLive(project.getLinkRepo())
                .linkLive(project.getLinkLive())
                .build();
    }

    public Project toEntity(ChildProjectRequestDto dto) {
        if (dto == null) return null;
        return Project
                .builder()
                .name(dto.getName())
                .employmentMode(dto.getEmploymentMode())
                .capacity(dto.getCapacity())
                .durationInMonths(dto.getDurationInMonths())
                .startYear(dto.getStartYear())
                .role(dto.getRole())
                .teamSize(dto.getTeamSize())
                .linkLive(dto.getLinkRepo())
                .linkLive(dto.getLinkLive())
                .build();
    }
}
