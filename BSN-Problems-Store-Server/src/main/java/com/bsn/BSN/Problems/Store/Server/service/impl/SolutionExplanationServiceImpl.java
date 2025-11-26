package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateSolutionExplanationRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.SolutionExplanationResponse;
import com.bsn.BSN.Problems.Store.Server.entity.ProblemSolution;
import com.bsn.BSN.Problems.Store.Server.entity.SolutionExplanation;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemSolutionRepository;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemSolutionExplanationRepository;
import com.bsn.BSN.Problems.Store.Server.service.SolutionExplanationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SolutionExplanationServiceImpl implements SolutionExplanationService {

    private final ProblemSolutionRepository problemSolutionRepository;
    private final ProblemSolutionExplanationRepository solutionExplanationRepository;

    /**
     * POST /approaches/{approachId}/explanation
     */
    @Override
    public SolutionExplanationResponse addExplanation(Long approachId, CreateSolutionExplanationRequest request) {
        ProblemSolution approach = problemSolutionRepository.findById(approachId)
                .orElseThrow(() -> new RuntimeException("Approach not found: " + approachId));

        // Either update existing explanation or create new
        SolutionExplanation explanation = solutionExplanationRepository
                .findByProblemSolution_SolutionId(approachId)
                .orElse(SolutionExplanation.builder().problemSolution(approach).build());

        explanation.setLogic(request.getLogic());
        explanation.setEdgeCases(request.getEdgeCases());
        explanation.setDryRun(request.getDryRun());
        explanation.setNotes(request.getNotes());

        SolutionExplanation saved = solutionExplanationRepository.save(explanation);
        return toResponse(saved);
    }

    /**
     * GET /approaches/{approachId}/explanation
     */
    @Override
    public SolutionExplanationResponse getExplanationByApproach(Long approachId) {
        SolutionExplanation explanation = solutionExplanationRepository
                .findByProblemSolution_SolutionId(approachId)
                .orElseThrow(() -> new RuntimeException("Explanation not found for approach: " + approachId));

        return toResponse(explanation);
    }

    // Mapper
    private SolutionExplanationResponse toResponse(SolutionExplanation e) {
        return SolutionExplanationResponse.builder()
                .explanationId(e.getExplanationId())
                .solutionId(e.getProblemSolution().getSolutionId())
                .logic(e.getLogic())
                .edgeCases(e.getEdgeCases())
                .dryRun(e.getDryRun())
                .notes(e.getNotes())
                .build();
    }
}
