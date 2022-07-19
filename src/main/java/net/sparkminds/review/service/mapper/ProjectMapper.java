package net.sparkminds.review.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.review.dto.request.ProjectRequestDto;
import net.sparkminds.review.dto.request.SubProjectRequestDto;
import net.sparkminds.review.dto.response.ProjectResponseDto;
import net.sparkminds.review.dto.response.SubProjectResponseDto;
import net.sparkminds.review.entity.Project;

@Service
public class ProjectMapper {
    public SubProjectRequestDto convertToChildRequestDto(Project project) {
        return SubProjectRequestDto.builder().name(project.getName()).employmentMode(project.getEmploymentMode())
                .capacity(project.getCapacity()).durationInMonths(project.getDurationInMonths())
                .startYear(project.getStartYear()).role(project.getRole()).teamSize(project.getTeamSize())
                .linkLive(project.getLinkRepo()).linkLive(project.getLinkLive()).build();
    }

    public Project convertToEntity(SubProjectRequestDto dto) {
        if (dto == null)
            return null;
        Project project = new Project();
        project.setName(dto.getName());
        project.setEmploymentMode(dto.getEmploymentMode());
        project.setCapacity(dto.getCapacity());
        project.setDurationInMonths(dto.getDurationInMonths());
        project.setStartYear(dto.getStartYear());
        project.setRole(dto.getRole());
        project.setTeamSize(dto.getTeamSize());
        project.setLinkRepo(dto.getLinkRepo());
        project.setLinkLive(dto.getLinkLive());
        project.setDelete(false);
        return project;
    }

    public Project convertToEntity(ProjectRequestDto dto) {
        if (dto == null)
            return null;
        Project project = new Project();
        project.setName(dto.getName());
        project.setEmploymentMode(dto.getEmploymentMode());
        project.setCapacity(dto.getCapacity());
        project.setDurationInMonths(dto.getDurationInMonths());
        project.setStartYear(dto.getStartYear());
        project.setRole(dto.getRole());
        project.setTeamSize(dto.getTeamSize());
        project.setLinkRepo(dto.getLinkRepo());
        project.setLinkLive(dto.getLinkLive());
        project.setDelete(false);
        return project;
    }

    public ProjectResponseDto convertToResponseDto(Project project) {
        return ProjectResponseDto.builder().name(project.getName()).employmentMode(project.getEmploymentMode())
                .capacity(project.getCapacity()).durationInMonths(project.getDurationInMonths())
                .startYear(project.getStartYear()).role(project.getRole()).teamSize(project.getTeamSize())
                .linkLive(project.getLinkRepo()).linkLive(project.getLinkLive()).profileId(project.getProfile().getId())
                .build();
    }

    public SubProjectResponseDto convertToSubResponseDto(Project project) {
        return SubProjectResponseDto.builder().name(project.getName()).employmentMode(project.getEmploymentMode())
                .capacity(project.getCapacity()).durationInMonths(project.getDurationInMonths())
                .startYear(project.getStartYear()).role(project.getRole()).teamSize(project.getTeamSize())
                .linkLive(project.getLinkRepo()).linkLive(project.getLinkLive()).build();
    }
}
