package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.service.TagService;
import com.example.communityboardrestspringreact.web.dto.response.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/tag")
@RestController
public class TagController {

    private final TagService tagService;

    @GetMapping("/bycommunity/{id}")
    public ResponseEntity<?> getByCommunity(@PathVariable Long id) {
        List<TagResponse> tags = tagService.getByCommunity(id);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

}
