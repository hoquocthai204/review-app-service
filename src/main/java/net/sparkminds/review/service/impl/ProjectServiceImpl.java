package net.sparkminds.review.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ProjectRequestDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.entity.Project;
import net.sparkminds.review.exception.ProfileNotFoundException;
import net.sparkminds.review.exception.ProjectNotFoundException;
import net.sparkminds.review.repository.ProfileRepository;
import net.sparkminds.review.repository.ProjectRepository;
import net.sparkminds.review.service.ProjectService;
import net.sparkminds.review.service.SendLogService;
import net.sparkminds.review.service.mapper.ProjectMapper;
import net.sparkminds.review.util.JwtTokenUtil;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProfileRepository profileRepository;
    private final ProjectMapper projectMapper;
    private final SendLogService sendLogService;

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAllByDeleteFalse();
    }

    @Override
    @Transactional
    public void addNewProject(ProjectRequestDto dto) {
        profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new ProfileNotFoundException("Profile is not found"));
        projectRepository.save(projectMapper.convertToEntity(dto));
        sendLogService.sendingMessage(null, null);
    }

    @Override
    @Transactional
    public void updateProject(Long id, ProjectRequestDto dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project is not found"));
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new ProfileNotFoundException("Profile is not found"));
        project.setName(dto.getName());
        project.setEmploymentMode(dto.getEmploymentMode());
        project.setCapacity(dto.getCapacity());
        project.setDurationInMonths(dto.getDurationInMonths());
        project.setStartYear(dto.getStartYear());
        project.setRole(dto.getRole());
        project.setTeamSize(dto.getTeamSize());
        project.setLinkRepo(dto.getLinkRepo());
        project.setLinkLive(dto.getLinkLive());
        project.setProfile(profile);
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project is not found"));
        project.setDelete(true);
    }

}
