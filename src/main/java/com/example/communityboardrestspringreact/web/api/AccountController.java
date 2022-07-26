package com.example.communityboardrestspringreact.web.api;

import com.example.communityboardrestspringreact.service.AccountService;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountEditRequest;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountRegisterRequest;
import com.example.communityboardrestspringreact.web.dto.response.CommonApiResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountResponse;
import com.example.communityboardrestspringreact.web.dto.response.account.AccountSearchResponse;
import com.example.communityboardrestspringreact.web.dto.search.account.AccountSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody AccountRegisterRequest request) {
        String token = accountService.register(request);
        return new ResponseEntity<>(CommonApiResponse.success(token, "신규 계정을 등록 하였습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> search(AccountSearch search, Pageable pageable) {
        Page<AccountSearchResponse> page = accountService.search(search, pageable);
        return new ResponseEntity<>(CommonApiResponse.success(page), HttpStatus.OK);
    }

    @GetMapping("/{token}")
    public ResponseEntity<?> get(@PathVariable String token) {
        AccountResponse response = accountService.getOne(token);
        return new ResponseEntity<>(CommonApiResponse.success(response), HttpStatus.OK);
    }

    @PutMapping("/{token}")
    public ResponseEntity<?> edit(@PathVariable String token, @RequestBody AccountEditRequest request) {
        accountService.edit(token, request);
        return new ResponseEntity<>(CommonApiResponse.success(token, "사용자 정보를 수정 하였습니다."), HttpStatus.OK);
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<?> delete(@PathVariable String token) {
        accountService.delete(token);
        return new ResponseEntity<>(CommonApiResponse.success(token, "사용자 정보를 삭제하였습니다."), HttpStatus.OK);
    }

}
