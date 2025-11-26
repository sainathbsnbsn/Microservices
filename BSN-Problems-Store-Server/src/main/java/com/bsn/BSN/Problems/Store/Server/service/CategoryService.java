package com.bsn.BSN.Problems.Store.Server.service;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateCategoryRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    /**
     * POST /categories
     * Creates a new category/pattern (e.g., Arrays, Graph, DP).
     */
    CategoryResponse createCategory(CreateCategoryRequest request);

    /**
     * GET /categories
     * Lists all categories for filters and admin management.
     */
    List<CategoryResponse> getAllCategories();
}
