package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.entity.*;
import com.bsn.BSN.Problems.Store.Server.repository.*;
import com.bsn.BSN.Problems.Store.Server.dto.request.*;
import com.bsn.BSN.Problems.Store.Server.dto.response.*;
import com.bsn.BSN.Problems.Store.Server.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final TagRepository tagRepository;
    private final ProblemTagMappingRepository problemTagMappingRepository;
    private final ProblemExampleRepository problemExampleRepository;
    private final ProblemConstraintRepository problemConstraintRepository;
    private final ProblemHintRepository problemHintRepository;

    // POST /problems
    @Override
    public ProblemResponse createProblem(CreateProblemRequest req) {
        Problem problem = Problem.builder()
                .title(req.getTitle())
                .slug(req.getSlug())
                .difficulty(req.getDifficulty())
                .difficultyScore(req.getDifficultyScore())
                .patterns(req.getPatterns())
                .topics(req.getTopics())
                .askedIn(req.getAskedIn())
                .isPremium(req.getIsPremium().equals("Y"))
                .status(req.getStatus())
                .problemStatement(req.getProblemStatement())
                .build();

        Problem saved = problemRepository.save(problem);
        return toResponse(saved);
    }

    // GET /problems
    @Override
    public Page<ProblemResponse> getAllProblems(
            String difficulty,
            String pattern,
            String topic,
            String company,
            String search,
            String status,
            String premium,
            Pageable pageable) {

        Page<Problem> page;

        if (search != null)
            page = problemRepository.findByTitleContainingIgnoreCase(search, pageable);
        else if (difficulty != null)
            page = problemRepository.findByDifficulty(difficulty, pageable);
        else if (pattern != null)
            page = problemRepository.findByPattern(pattern, pageable);
        else if (topic != null)
            page = problemRepository.findByTopic(topic, pageable);
        else if (company != null)
            page = problemRepository.findByCompany(company, pageable);
        else if (status != null)
            page = problemRepository.findByStatus(status, pageable);
        else if (premium != null)
            page = problemRepository.findByIsPremium(premium.equals("Y"), pageable);
        else
            page = problemRepository.findAll(pageable);

        return page.map(this::toResponse);
    }

    // GET /problems/{problemId}
    @Override
    public ProblemResponse getProblemById(Long problemId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        return toResponse(problem);
    }

    // PATCH /problems/{problemId}
    @Override
    public ProblemResponse updateProblem(Long problemId, UpdateProblemRequest req) {
        Problem p = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        if (req.getTitle() != null) p.setTitle(req.getTitle());
        if (req.getDifficulty() != null) p.setDifficulty(req.getDifficulty());
        if (req.getDifficultyScore() != null) p.setDifficultyScore(req.getDifficultyScore());
        if (req.getPatterns() != null) p.setPatterns(req.getPatterns());
        if (req.getTopics() != null) p.setTopics(req.getTopics());
        if (req.getAskedIn() != null) p.setAskedIn(req.getAskedIn());
        if (req.getIsPremium() != null) p.setIsPremium(req.getIsPremium().equals("Y"));
        if (req.getStatus() != null) p.setStatus(req.getStatus());
        if (req.getProblemStatement() != null) p.setProblemStatement(req.getProblemStatement());

        return toResponse(problemRepository.save(p));
    }

    // POST /problems/{problemId}/attempt
    @Override
    public AttemptIncrementResponse incrementAttempt(Long problemId) {
        problemRepository.incrementAttempts(problemId);
        Problem p = problemRepository.findById(problemId).get();

        return AttemptIncrementResponse.builder()
                .problemId(problemId)
                .attemptCount(p.getAttemptCount())
                .build();
    }

    // POST /problems/{problemId}/success-rate
    @Override
    public SuccessRateResponse updateSuccessRate(Long problemId, SuccessRateUpdateRequest request) {
        Problem p = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        p.setSuccessRate(request.getSuccessRate());
        problemRepository.save(p);

        return SuccessRateResponse.builder()
                .problemId(problemId)
                .successRate(request.getSuccessRate())
                .build();
    }

    // GET /problems/{problemId}/tags
    @Override
    public ProblemTagsListResponse getProblemTags(Long problemId) {
        List<ProblemTagMapping> mappings =
                problemTagMappingRepository.findByProblemId(problemId);

        List<TagResponse> tags = mappings.stream()
                .map(m -> TagResponse.builder()
                        .tagId(m.getTag().getTagId())
                        .tagName(m.getTag().getTagName())
                        .description(m.getTag().getDescription())
                        .build())
                .collect(Collectors.toList());

        return ProblemTagsListResponse.builder()
                .problemId(problemId)
                .tags(tags)
                .build();
    }

    // POST /problems/{problemId}/tags/{tagId}
    @Override
    public ProblemTagMappingResponse mapTagToProblem(Long problemId, Long tagId) {
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        ProblemTagKey id = new ProblemTagKey(problemId, tagId);
        ProblemTagMapping mapping = new ProblemTagMapping(id, problem, tag);

        problemTagMappingRepository.save(mapping);

        return ProblemTagMappingResponse.builder()
                .problemId(problemId)
                .tagId(tagId)
                .mapped(true)
                .build();
    }

    // DELETE /problems/{problemId}
    @Override
    public Boolean deleteProblem(Long problemId) {
        problemRepository.deleteById(problemId);
        return true;
    }


    // -------------------------
    // Utility mapper method
    // -------------------------
    private ProblemResponse toResponse(Problem p) {
        return ProblemResponse.builder()
                .problemId(p.getProblemId())
                .title(p.getTitle())
                .slug(p.getSlug())
                .difficulty(p.getDifficulty())
                .difficultyScore(p.getDifficultyScore())
                .patterns(p.getPatterns())
                .topics(p.getTopics())
                .askedIn(p.getAskedIn())
                .isPremium(p.getIsPremium() ? "Y" : "N")
                .status(p.getStatus())
                .popularityScore(p.getPopularityScore())
                .attemptCount(p.getAttemptCount())
                .successRate(p.getSuccessRate())
                .frequencyTag(p.getFrequencyTag())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}

