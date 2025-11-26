package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.SolutionExplanation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemSolutionExplanationRepository extends JpaRepository<SolutionExplanation, Long> {
    Optional<SolutionExplanation> findByProblemSolution_SolutionId(Long approachId);
}

