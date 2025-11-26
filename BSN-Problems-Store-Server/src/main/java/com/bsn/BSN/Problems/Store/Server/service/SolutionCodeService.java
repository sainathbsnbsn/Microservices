package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateSolutionCodeRequest;
import com.bsn.BSN.Problems.Store.Server.dto.request.SolutionCodeBatchRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionCodeBatchResponse;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionCodeResponse;

import java.util.List;

public interface SolutionCodeService {

    /**
     * POST /approaches/{approachId}/codes
     * Adds a single language implementation for the given approach.
     */
    SolutionCodeResponse addCode(Long approachId, CreateSolutionCodeRequest request);

    /**
     * POST /approaches/{approachId}/codes/batch
     * Adds multiple language implementations (e.g., Java, C++, Python) at once.
     */
    SolutionCodeBatchResponse addCodesBatch(Long approachId, SolutionCodeBatchRequest request);

    /**
     * GET /approaches/{approachId}/codes
     * Returns all code implementations for a given approach.
     */
    List<SolutionCodeResponse> getCodesByApproach(Long approachId);

    /**
     * DELETE /codes/{codeId}
     * Deletes a single code implementation by its id.
     */
    Boolean deleteCode(Long codeId);
}
