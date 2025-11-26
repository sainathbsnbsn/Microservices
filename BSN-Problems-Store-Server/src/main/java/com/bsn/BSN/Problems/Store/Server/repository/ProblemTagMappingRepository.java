package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemTagKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.bsn.BSN.Problems.Store.Server.entity.ProblemTagMapping;

public interface ProblemTagMappingRepository extends JpaRepository<ProblemTagMapping, ProblemTagKey> {
    List<ProblemTagMapping> findByProblemId(Long problemId);
}

