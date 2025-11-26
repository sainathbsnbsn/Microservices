package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.SolutionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface SolutionCodeRepository extends JpaRepository<SolutionCode, Long> {

    // Get all code versions of an approach
    List<SolutionCode> findBySolution_SolutionId(Long solutionId);

    // Get specific language code version
    Optional<SolutionCode> findBySolution_SolutionIdAndLanguage(Long solutionId, String language);

    // Admin: delete codes by solutionId
    @Modifying
    @Query("DELETE FROM SolutionCode c WHERE c.solution.solutionId = :solutionId")
    void deleteBySolutionId(@Param("solutionId") Long solutionId);
}

