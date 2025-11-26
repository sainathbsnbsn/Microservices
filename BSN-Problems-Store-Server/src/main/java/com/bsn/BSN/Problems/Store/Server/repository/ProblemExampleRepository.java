package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemExample;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProblemExampleRepository extends JpaRepository<ProblemExample, Long> {
    List<ProblemExample> findByProblem_ProblemId(Long problemId);
}

