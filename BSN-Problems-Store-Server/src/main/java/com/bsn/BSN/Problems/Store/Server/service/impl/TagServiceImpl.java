package com.bsn.BSN.Problems.Store.Server.service.impl;

import com.bsn.BSN.Problems.Store.Server.dto.request.CreateTagRequest;
import com.bsn.BSN.Problems.Store.Server.dto.response.TagResponse;
import com.bsn.BSN.Problems.Store.Server.entity.Tag;
import com.bsn.BSN.Problems.Store.Server.repository.TagRepository;
import com.bsn.BSN.Problems.Store.Server.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    /**
     * POST /tags
     */
    @Override
    public TagResponse createTag(CreateTagRequest request) {
        Tag tag = Tag.builder()
                .tagName(request.getTagName())
                .description(request.getDescription())
                .build();

        Tag saved = tagRepository.save(tag);
        return toResponse(saved);
    }

    /**
     * GET /tags
     */
    @Override
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TagResponse toResponse(Tag t) {
        return TagResponse.builder()
                .tagId(t.getTagId())
                .tagName(t.getTagName())
                .description(t.getDescription())
                .build();
    }
}
