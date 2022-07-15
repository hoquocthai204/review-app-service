package net.sparkminds.review.service;

import java.util.List;

import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;

public interface ProfileService {

    List<Profile> getAllProfile();

    Profile addNewProfile(ProfileRequestDto profileRequestDto);

    void updateProfile(Long id, ProfileRequestDto profileRequestDto);

    void deleteProfile(Long id);

    Object exportProfile();

}