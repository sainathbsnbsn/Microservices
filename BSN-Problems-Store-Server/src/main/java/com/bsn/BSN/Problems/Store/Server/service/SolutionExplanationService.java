package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateSolutionExplanationRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionExplanationResponse;

public interface SolutionExplanationService {

    /**
     * POST /approaches/{approachId}/explanation
     * Adds (or replaces) explanation for a given approach.
     */
    SolutionExplanationResponse addExplanation(Long approachId, CreateSolutionExplanationRequest request);

    /**
     * GET /approaches/{approachId}/explanation
     * Returns explanation for a given approach (logic, edge cases, dry run).
     */
    SolutionExplanationResponse getExplanationByApproach(Long approachId);
}
