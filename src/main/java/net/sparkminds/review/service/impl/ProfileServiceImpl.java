package net.sparkminds.review.service.impl;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.ProfileRequestDto;
import net.sparkminds.review.dto.response.ProfileResponseDto;
import net.sparkminds.review.entity.Profile;
import net.sparkminds.review.exception.ProfileNotFoundException;
import net.sparkminds.review.repository.ProfileRepository;
import net.sparkminds.review.service.ProfileService;
import net.sparkminds.review.service.SendLogService;
import net.sparkminds.review.service.mapper.ProfileMapper;
import net.sparkminds.review.util.JwtTokenUtil;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final SendLogService sendLogService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Profile> getAllProfile(HttpHeaders headers) {
        String requestTokenHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
        String jwtToken = requestTokenHeader.substring(7);
        sendLogService.sendingMessage(jwtTokenUtil.getUsernameFromToken(jwtToken), "get all Profile");
        return profileRepository.findAll();
    }

    @Override
    @Transactional
    public ProfileResponseDto addNewProfile(ProfileRequestDto dto) {
        if (profileRepository.existsByEmailAddress(dto.getEmailAddress())) {
            profileRepository.deleteByEmailAddress(dto.getEmailAddress());
        }
        sendLogService.sendingMessage(dto.getEmailAddress(), "create new profile");
        return saveProfile(dto);
    }

    @Override
    @Transactional
    public void updateProfile(Long id, ProfileRequestDto dto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile is not found"));
        if (!profileRepository.existsByEmailAddress(dto.getEmailAddress())) {
            profileRepository.deleteByEmailAddress(dto.getEmailAddress());
            saveProfile(dto);
            return;
        }
        profile.setName(dto.getName());
        profile.setGithubUser(dto.getGithubUser());
        profile.setEmailAddress(dto.getEmailAddress());
        sendLogService.sendingMessage(dto.getEmailAddress(), "just update profile");
    }

    public ProfileResponseDto saveProfile(ProfileRequestDto dto) {
        Profile profile = profileRepository.save(profileMapper.convertToEntity(dto));
        Profile profileMod = profileRepository.findById(profile.getId()).get();
        profileMod.getPastProjects().forEach(project -> project.setProfile(profileMod));
        return profileMapper.convertToResponseDto(profile);
    }

    @Override
    @Transactional
    public void deleteProfile(Long id) {
        profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile is not found to delete"));
        profileRepository.deleteById(id);
    }

    @Override
    public Profile findProfile(Long id) {
        return profileRepository.findById(id).get();
    }

}
