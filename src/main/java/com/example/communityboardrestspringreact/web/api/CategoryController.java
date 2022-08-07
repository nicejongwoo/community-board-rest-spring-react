package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.service.CategoryService;
import com.example.communityboardrestspringreact.web.dto.request.CategoryRequest;
import com.example.communityboardrestspringreact.web.dto.response.CategoryResponse;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.search.CategorySearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody CategoryRequest request) {
        Long id = categoryService.register(request);
        return new ResponseEntity<>(CommonApiResponse.success(id, "등록 되었습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(CategorySearch search, Pageable pageable) {
        Page<CategoryResponse> page = categoryService.search(search, pageable);
        return new ResponseEntity<>(CommonApiResponse.success(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        CategoryResponse response = categoryService.getOne(id);
        return new ResponseEntity<>(CommonApiResponse.success(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CategoryRequest request) {
        Category category = categoryService.checkCategory(id);
        categoryService.edit(request, category);
        return new ResponseEntity<>(CommonApiResponse.success(id, "수정 되었습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Category category = categoryService.checkCategory(id);
        categoryService.delete(category);
        return new ResponseEntity<>(CommonApiResponse.success(id, "삭제 되었습니다."), HttpStatus.OK);
    }

}
