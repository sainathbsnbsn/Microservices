package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProblemSolutionRepository extends JpaRepository<ProblemSolution, Long> {

    // All approaches of a problem
    List<ProblemSolution> findByProblem_ProblemId(Long problemId);

    // Fetch by tag (good/better/best)
    List<ProblemSolution> findByProblem_ProblemIdAndTag(Long problemId, String tag);
}

