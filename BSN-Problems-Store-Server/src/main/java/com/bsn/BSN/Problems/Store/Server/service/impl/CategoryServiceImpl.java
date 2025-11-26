package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateCategoryRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.CategoryResponse;
import com.bsn.BSN.Problems.Store.Server.entity.ProblemCategory;
import com.bsn.BSN.Problems.Store.Server.repository.ProblemCategoryRepository;
import com.bsn.BSN.Problems.Store.Server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final ProblemCategoryRepository problemCategoryRepository;

    /**
     * POST /categories
     */
    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        ProblemCategory category = ProblemCategory.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .build();

        ProblemCategory saved = problemCategoryRepository.save(category);
        return toResponse(saved);
    }

    /**
     * GET /categories
     */
    @Override
    public List<CategoryResponse> getAllCategories() {
        return problemCategoryRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private CategoryResponse toResponse(ProblemCategory c) {
        return CategoryResponse.builder()
                .categoryId(c.getCategoryId())
                .categoryName(c.getCategoryName())
                .description(c.getDescription())
                .build();
    }
}
