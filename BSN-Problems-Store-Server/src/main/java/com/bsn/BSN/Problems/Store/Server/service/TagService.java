package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateTagRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.TagResponse;

import java.util.List;

public interface TagService {

    /**
     * POST /tags
     * Creates a new tag (e.g., "sliding window", "hashing", "top 100").
     */
    TagResponse createTag(CreateTagRequest request);

    /**
     * GET /tags
     * Returns all tags for filters and admin UI.
     */
    List<TagResponse> getAllTags();
}
