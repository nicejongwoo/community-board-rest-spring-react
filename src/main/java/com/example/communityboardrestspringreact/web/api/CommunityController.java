package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.service.CommunityService;
import com.example.communityboardrestspringreact.web.dto.request.CommunityRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommunityListResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommunityResponse;
import com.example.communityboardrestspringreact.web.dto.search.CommunitySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/community")
@RestController
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody CommunityRequest request) {
        Long id = communityService.register(request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(CommunitySearch search, Pageable pageable) {
        Page<CommunityListResponse> page = communityService.search(search, pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        CommunityResponse response = communityService.getOne(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CommunityRequest request) {
        Community community = communityService.checkCommunity(id);
        communityService.edit(request, community);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Community community = communityService.checkCommunity(id);
        communityService.delete(community);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
