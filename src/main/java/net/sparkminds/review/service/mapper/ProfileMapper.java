package net.sparkminds.review.service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.dto.response.ProfileResponseDto;
import net.sparkminds.review.entity.Profile;

@Service
@AllArgsConstructor
public class ProfileMapper {
    
    private final ProjectMapper projectMapper;

    public ProfileRequestDto convertToRequestDto(Profile profile) {
        return ProfileRequestDto
                .builder()
                .emailAddress(profile.getEmailAddress())
                .name(profile.getName())
                .githubUser(profile.getGithubUser())
                .pastProjects(profile.getPastProjects().stream().map(projectMapper::convertToChildRequestDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public Profile convertToEntity(ProfileRequestDto dto) {
        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setEmailAddress(dto.getEmailAddress());
        profile.setGithubUser(dto.getGithubUser());
        profile.setPastProjects(
                dto.getPastProjects().stream().map(projectMapper::convertToEntity).collect(Collectors.toList()));
        return profile;
    }

    public ProfileResponseDto convertToResponseDto(Profile profile) {
        return ProfileResponseDto
                .builder()
                .emailAddress(profile.getEmailAddress())
                .name(profile.getName())
                .githubUser(profile.getGithubUser())
                .pastProjects(profile.getPastProjects().stream().map(projectMapper::convertToSubResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
