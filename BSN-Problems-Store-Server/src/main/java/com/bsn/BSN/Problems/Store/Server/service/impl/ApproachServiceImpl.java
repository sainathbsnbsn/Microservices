package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateApproachRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.ApproachResponse;
import com.bsn.BSN.Problems.Store.Server.entity.Problem;
import com.bsn.BSN.Problems.Store.Server.entity.ProblemSolution;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemRepository;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemSolutionRepository;
import com.bsn.BSN.Problems.Store.Server.service.ApproachService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApproachServiceImpl implements ApproachService {

    private final ProblemRepository problemRepository;
    private final ProblemSolutionRepository problemSolutionRepository;

    /**
     * POST /problems/{problemId}/approaches
     */
    @Override
    public ApproachResponse createApproach(Long problemId, CreateApproachRequest request) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found: " + problemId));

        ProblemSolution solution = ProblemSolution.builder()
                .problem(problem)
                .approachName(request.getApproachName())
                .tag(request.getTag()) // "good", "better", "best"
                .timeComplexity(request.getTimeComplexity())
                .spaceComplexity(request.getSpaceComplexity())
                .explanation(request.getExplanation())
                .build();

        ProblemSolution saved = problemSolutionRepository.save(solution);
        return toResponse(saved);
    }

    /**
     * GET /problems/{problemId}/approaches
     */
    @Override
    public List<ApproachResponse> getApproachesByProblemId(Long problemId) {
        List<ProblemSolution> solutions =
                problemSolutionRepository.findByProblem_ProblemId(problemId);
        return solutions.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * DELETE /approaches/{approachId}
     */
    @Override
    public Boolean deleteApproach(Long approachId) {
        if (!problemSolutionRepository.existsById(approachId)) {
            return false;
        }
        problemSolutionRepository.deleteById(approachId);
        return true;
    }

    // Mapper
    private ApproachResponse toResponse(ProblemSolution s) {
        return ApproachResponse.builder()
                .approachId(s.getSolutionId())
                .problemId(s.getProblem().getProblemId())
                .approachName(s.getApproachName())
                .tag(s.getTag())
                .timeComplexity(s.getTimeComplexity())
                .spaceComplexity(s.getSpaceComplexity())
                .explanation(s.getExplanation())
                .build();
    }
}
