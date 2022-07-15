package net.sparkminds.review.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;

@Service
public class ProfileMapper {

    public ProfileRequestDto toProfileRequestDto(Profile profile) {
        return ProfileRequestDto
                .builder()
                .emailAddress(profile.getEmailAddress())
                .name(profile.getName())
                .githubUser(profile.getGithubUser())
                .build();
    }

    public Profile toEntity(ProfileRequestDto dto) {
        return Profile
                .builder()
                .name(dto.getName())
                .emailAddress(dto.getEmailAddress())
                .githubUser(dto.getGithubUser())
                .build();
    }
}
