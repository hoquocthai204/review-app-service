package net.sparkminds.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sparkminds.review.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    void deleteByEmailAddress(String emailAddress);

    Boolean existsByEmailAddress(String emailAddress);
}
