package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.*;
import com.bsn.BSN.Problems.Store.Server.dto.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProblemService {

    // POST /problems
    ProblemResponse createProblem(CreateProblemRequest request);

    // GET /problems
    Page<ProblemResponse> getAllProblems(
            String difficulty,
            String pattern,
            String topic,
            String company,
            String search,
            String status,
            String premium,
            Pageable pageable);

    // GET /problems/{problemId}
    ProblemResponse getProblemById(Long problemId);

    // PATCH /problems/{problemId}
    ProblemResponse updateProblem(Long problemId, UpdateProblemRequest request);

    // POST /problems/{problemId}/attempt
    AttemptIncrementResponse incrementAttempt(Long problemId);

    // POST /problems/{problemId}/success-rate
    SuccessRateResponse updateSuccessRate(Long problemId, SuccessRateUpdateRequest request);

    // GET /problems/{problemId}/tags
    ProblemTagsListResponse getProblemTags(Long problemId);

    // POST /problems/{problemId}/tags/{tagId}
    ProblemTagMappingResponse mapTagToProblem(Long problemId, Long tagId);

    // DELETE /problems/{problemId}
    Boolean deleteProblem(Long problemId);
}

