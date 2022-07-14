package net.sparkminds.review.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.exception.ProfileNotFoundException;
import net.sparkminds.review.repository.ProfileRepository;
import net.sparkminds.review.service.ProfileService;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }

    @Override
    @Transactional
    public Profile addNewProfile(ProfileRequestDto profileRequestDto) {
        if (profileRepository.existsByEmailAddress(profileRequestDto.getEmailAddress())) {
            profileRepository.deleteByEmailAddress(profileRequestDto.getEmailAddress());
        }
        return saveProfile(profileRequestDto);
    }

    @Override
    @Transactional
    public void updateProfile(Long id, ProfileRequestDto profileRequestDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile is not found"));
        if (!profileRepository.existsByEmailAddress(profileRequestDto.getEmailAddress())) {
            profileRepository.deleteByEmailAddress(profileRequestDto.getEmailAddress());
            saveProfile(profileRequestDto);
            return;
        }
        profile.setName(profileRequestDto.getName());
        profile.setGithubUser(profileRequestDto.getGithubUser());
        profile.setEmailAddress(profileRequestDto.getEmailAddress());
    }

    public Profile saveProfile(ProfileRequestDto profileRequestDto) {
        return profileRepository.save(Profile.builder().emailAddress(profileRequestDto.getEmailAddress())
                .name(profileRequestDto.getName()).githubUser(profileRequestDto.getGithubUser()).build());
    }

    @Override
    @Transactional
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Object exportProfile() {
        return null;
    }

}
