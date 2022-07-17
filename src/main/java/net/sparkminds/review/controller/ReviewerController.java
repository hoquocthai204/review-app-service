package net.sparkminds.review.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.review.dto.request.ReviewerRequestDto;
import net.sparkminds.review.service.ReviewerService;

@RestController
@RequestMapping("/api/reviewers")
@RequiredArgsConstructor
@Validated
public class ReviewerController {

    private final ReviewerService reviewerService;

    @PostMapping
    public ResponseEntity<?> addNewReviewer(@Valid @RequestBody ReviewerRequestDto dto) {
        reviewerService.addNewReviewer(dto);
        return ResponseEntity.noContent().build();
    }

}
