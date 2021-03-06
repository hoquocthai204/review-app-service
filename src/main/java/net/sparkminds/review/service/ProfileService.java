package net.sparkminds.review.service;

import java.util.List;

import org.springframework.http.HttpHeaders;

import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.dto.response.ProfileResponseDto;
import net.sparkminds.review.entity.Profile;

public interface ProfileService {

    List<Profile> getAllProfile(HttpHeaders headers);

    ProfileResponseDto addNewProfile(ProfileRequestDto profileRequestDto);

    void updateProfile(Long id, ProfileRequestDto profileRequestDto);

    void deleteProfile(Long id);

    Profile findProfile(Long id);

}
