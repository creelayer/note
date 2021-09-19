package com.ohmynone.rest.controller;

import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.dto.TagDto;
import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.entity.Tag;
import com.ohmynone.rest.mapper.TagMapper;
import com.ohmynone.rest.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper mapper;

    @GetMapping("")
    ResponseEntity<?> index(@AuthenticationPrincipal Identity identity) {
        return ResponseEntity.ok(new Response<>(mapper.map(tagService.findAllByIdentity(identity))));
    }

    @PostMapping("/{id}")
    @PreAuthorize("#tag.identity.username == principal.username")
    ResponseEntity<?> updateTag(@PathVariable("id") Tag tag,
                                @Valid @RequestBody TagDto dto) {
        tag = mapper.map(dto, tag);
        return ResponseEntity.ok(new Response<>(mapper.map(tagService.save(tag))));
    }
}
