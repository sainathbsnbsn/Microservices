package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemHintRepository extends JpaRepository<ProblemHint, Long> {

    // Hints sorted by order
    @Query("SELECT h FROM ProblemHint h WHERE h.problem.problemId = :problemId ORDER BY h.hintOrder ASC")
    List<ProblemHint> findByProblemIdOrdered(@Param("problemId") Long problemId);
}

