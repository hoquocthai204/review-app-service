package net.sparkminds.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.sparkminds.review.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
