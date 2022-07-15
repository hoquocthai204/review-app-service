package net.sparkminds.review.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.entity.Project;
import net.sparkminds.review.exception.ProfileNotFoundException;
import net.sparkminds.review.repository.ProfileRepository;
import net.sparkminds.review.service.ProfileService;
import net.sparkminds.review.service.mapper.ProjectMapper;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }

    @Override
    @Transactional
    public Profile addNewProfile(ProfileRequestDto dto) {
        if (profileRepository.existsByEmailAddress(dto.getEmailAddress())) {
            profileRepository.deleteByEmailAddress(dto.getEmailAddress());
        }
//        return profileRepository.save(Profile.builder().emailAddress(dto.getEmailAddress()).name(dto.getName())
//                .githubUser(dto.getGithubUser()).build());
        return saveProfile(dto);
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

    public Profile saveProfile(ProfileRequestDto dto) {
        List<Project> list = dto.getPastProjects().stream().map(projectMapper::toEntity).collect(Collectors.toList());
        System.out.println(list);
        return profileRepository
                .save(Profile
                .builder()
                .emailAddress(dto.getEmailAddress())
                .name(dto.getName())
                .githubUser(dto.getGithubUser())
                .pastProjects(list)
                .build());
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
