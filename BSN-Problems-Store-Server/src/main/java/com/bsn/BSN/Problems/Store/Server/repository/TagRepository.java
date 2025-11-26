package com.bsn.BSN.Problems.Store.Server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bsn.BSN.Problems.Store.Server.entity.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagNameIgnoreCase(String tagName);
}

