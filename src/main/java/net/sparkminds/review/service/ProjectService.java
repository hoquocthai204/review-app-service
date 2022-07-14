package net.sparkminds.review.service;

import java.util.List;

import net.sparkminds.review.dto.request.ProjectRequestDto;
import net.sparkminds.review.entity.Project;

public interface ProjectService {

    List<Project> getAllProject();

    void addNewProject(ProjectRequestDto dto);

    void updateProject(Long id, ProjectRequestDto dto);

    void deleteProject(Long id);

}
