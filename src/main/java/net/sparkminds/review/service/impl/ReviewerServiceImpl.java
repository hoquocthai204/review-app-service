package net.sparkminds.review.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ReviewerRequestDto;
import net.sparkminds.review.entity.Reviewer;
import net.sparkminds.review.repository.ReviewerRepository;
import net.sparkminds.review.service.ReviewerService;
import net.sparkminds.review.service.mapper.ReviewerMapper;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ReviewerServiceImpl implements ReviewerService {

    private final ReviewerRepository reviewerRepository;
    private final ReviewerMapper reviewerMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void addNewReviewer(ReviewerRequestDto dto) {
        Optional<Reviewer> reviewerOpt = reviewerRepository.findByEmail(dto.getEmail());
        if (reviewerOpt.isPresent()) {
            reviewerOpt.get().setName(dto.getName());
            reviewerOpt.get().setPassword(encoder.encode(dto.getPassword()));
            reviewerOpt.get().setPicture(dto.getPicture());
            return;
        }
        reviewerRepository.save(reviewerMapper.convertToEntity(dto));
    }

}
