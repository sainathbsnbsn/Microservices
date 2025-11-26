package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProblemConstraintRepository extends JpaRepository<ProblemConstraint, Long> {
    List<ProblemConstraint> findByProblem_ProblemId(Long problemId);
}

