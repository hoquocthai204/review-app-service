package net.sparkminds.review.service;

import net.sparkminds.review.dto.request.ReviewerRequestDto;

public interface ReviewerService {
    void addNewReviewer(ReviewerRequestDto dto);
}
