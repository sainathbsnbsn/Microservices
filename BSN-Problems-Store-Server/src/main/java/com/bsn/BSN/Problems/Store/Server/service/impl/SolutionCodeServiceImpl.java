package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateSolutionCodeRequest;
import com.bsn.BSN.Problems.Store.Server.dto.request.SolutionCodeBatchRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionCodeBatchResponse;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionCodeResponse;
import com.bsn.BSN.Problems.Store.Server.entity.ProblemSolution;
import com.bsn.BSN.Problems.Store.Server.entity.SolutionCode;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemSolutionRepository;
import com.bsn.BSN.Problems.Store.Server.repository.SolutionCodeRepository;
import com.bsn.BSN.Problems.Store.Server.service.SolutionCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SolutionCodeServiceImpl implements SolutionCodeService {

    private final ProblemSolutionRepository problemSolutionRepository;
    private final SolutionCodeRepository solutionCodeRepository;

    /**
     * POST /approaches/{approachId}/codes
     */
    @Override
    public SolutionCodeResponse addCode(Long approachId, CreateSolutionCodeRequest request) {
        ProblemSolution approach = problemSolutionRepository.findById(approachId)
                .orElseThrow(() -> new RuntimeException("Approach not found: " + approachId));

        SolutionCode code = SolutionCode.builder()
                .solution(approach)
                .language(request.getLanguage())
                .codeText(request.getCodeText())
                .build();

        SolutionCode saved = solutionCodeRepository.save(code);
        return toResponse(saved);
    }

    /**
     * POST /approaches/{approachId}/codes/batch
     */
    @Override
    public SolutionCodeBatchResponse addCodesBatch(Long approachId, SolutionCodeBatchRequest request) {
        int count = 0;
        for (CreateSolutionCodeRequest codeRequest : request.getCodes()) {
            addCode(approachId, codeRequest);
            count++;
        }
        return SolutionCodeBatchResponse.builder()
                .solutionId(approachId)
                .codesAdded(count)
                .build();
    }

    /**
     * GET /approaches/{approachId}/codes
     */
    @Override
    public List<SolutionCodeResponse> getCodesByApproach(Long approachId) {
        List<SolutionCode> codes =
                solutionCodeRepository.findBySolution_SolutionId(approachId);
        return codes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * DELETE /codes/{codeId}
     */
    @Override
    public Boolean deleteCode(Long codeId) {
        if (!solutionCodeRepository.existsById(codeId)) {
            return false;
        }
        solutionCodeRepository.deleteById(codeId);
        return true;
    }

    // Mapper
    private SolutionCodeResponse toResponse(SolutionCode c) {
        return SolutionCodeResponse.builder()
                .codeId(c.getCodeId())
                .solutionId(c.getSolution().getSolutionId())
                .language(c.getLanguage())
                .codeText(c.getCodeText())
                .build();
    }
}
