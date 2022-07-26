package net.sparkminds.review.service.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sparkminds.review.dto.request.ReviewerRequestDto;
import net.sparkminds.review.entity.Reviewer;

@Service
@AllArgsConstructor
public class ReviewerMapper {
    
    private final PasswordEncoder encoder;

    public Reviewer convertToEntity(ReviewerRequestDto dto) {
        Reviewer reviewer = new Reviewer();
        reviewer.setName(dto.getName());
        reviewer.setEmail(dto.getEmail());
        reviewer.setPassword(encoder.encode(dto.getPassword()));
        reviewer.setPicture(dto.getPicture());
        return reviewer;
    }
}
