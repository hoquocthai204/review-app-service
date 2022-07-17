package net.sparkminds.review.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sparkminds.review.entity.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

    Optional<Reviewer> findByEmail(String email);
//    Optional<Reviewer> findByNameAndEmailAndPassword(String name, String email, String password);
}
