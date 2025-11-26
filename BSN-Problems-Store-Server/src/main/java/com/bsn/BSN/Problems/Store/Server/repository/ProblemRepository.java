package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    // Search with title substring
    Page<Problem> findByTitleContainingIgnoreCase(String search, Pageable pageable);

    // Filter by difficulty
    Page<Problem> findByDifficulty(String difficulty, Pageable pageable);

    // Filter by a single pattern (comma-separated storage)
    @Query("""
      SELECT p FROM Problem p
      WHERE LOWER(p.patterns) LIKE LOWER(CONCAT('%,', :pattern, ',%'))
         OR LOWER(p.patterns) LIKE LOWER(CONCAT(:pattern, ',%'))
         OR LOWER(p.patterns) LIKE LOWER(CONCAT('%,', :pattern))
         OR LOWER(p.patterns) = LOWER(:pattern)
    """)
    Page<Problem> findByPattern(@Param("pattern") String pattern, Pageable pageable);

    // Filter by asked company
    @Query("""
      SELECT p FROM Problem p
      WHERE LOWER(p.askedIn) LIKE LOWER(CONCAT('%,', :company, ',%'))
         OR LOWER(p.askedIn) LIKE LOWER(CONCAT(:company, ',%'))
         OR LOWER(p.askedIn) LIKE LOWER(CONCAT('%,', :company))
         OR LOWER(p.askedIn) = LOWER(:company)
    """)
    Page<Problem> findByCompany(@Param("company") String company, Pageable pageable);

    // Filter by topic
    @Query("""
      SELECT p FROM Problem p
      WHERE LOWER(p.topics) LIKE LOWER(CONCAT('%,', :topic, ',%'))
         OR LOWER(p.topics) LIKE LOWER(CONCAT(:topic, ',%'))
         OR LOWER(p.topics) LIKE LOWER(CONCAT('%,', :topic))
         OR LOWER(p.topics) = LOWER(:topic)
    """)
    Page<Problem> findByTopic(@Param("topic") String topic, Pageable pageable);

    // Only ACTIVE or specific status
    Page<Problem> findByStatus(String status, Pageable pageable);

    // Premium filter
    Page<Problem> findByIsPremium(Boolean isPremium, Pageable pageable);

    // Increment attempt count
    @Modifying
    @Query("UPDATE Problem p SET p.attemptCount = p.attemptCount + 1 WHERE p.problemId = :problemId")
    void incrementAttempts(@Param("problemId") Long problemId);

    // Sort options using JPQL + pageable
    @Query("SELECT p FROM Problem p ORDER BY p.popularityScore DESC")
    Page<Problem> findTrending(Pageable pageable);

    @Query("SELECT p FROM Problem p ORDER BY p.difficultyScore DESC")
    Page<Problem> findHardFirst(Pageable pageable);

    @Query("SELECT p FROM Problem p ORDER BY p.successRate DESC")
    Page<Problem> findHighSuccessRate(Pageable pageable);
}

