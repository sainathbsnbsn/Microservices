package com.bsn.BSN.Problems.Store.Server.repository;

import com.bsn.BSN.Problems.Store.Server.entity.ProblemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProblemCategoryRepository extends JpaRepository<ProblemCategory, Long> {

    // Optional helper: find category by name for filtering/admin
    Optional<ProblemCategory> findByCategoryNameIgnoreCase(String categoryName);
}
