package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateApproachRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.ApproachResponse;

import java.util.List;

public interface ApproachService {

    /**
     * POST /problems/{problemId}/approaches
     * Adds a new approach (good / better / best) for a given problem.
     */
    ApproachResponse createApproach(Long problemId, CreateApproachRequest request);

    /**
     * GET /problems/{problemId}/approaches
     * Returns all approaches (good / better / best) for a problem.
     */
    List<ApproachResponse> getApproachesByProblemId(Long problemId);

    /**
     * DELETE /approaches/{approachId}
     * Deletes an approach and (by cascade) its codes/explanation.
     */
    Boolean deleteApproach(Long approachId);
}
