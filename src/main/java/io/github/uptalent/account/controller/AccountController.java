package io.github.uptalent.account.controller;

import io.github.uptalent.account.model.common.Author;
import io.github.uptalent.account.model.enums.Role;
import io.github.uptalent.account.model.request.AuthLogin;
import io.github.uptalent.account.model.request.AuthRegister;
import io.github.uptalent.account.model.response.AuthResponse;
import io.github.uptalent.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static io.github.uptalent.starter.util.Constants.USER_ID_KEY;
import static io.github.uptalent.starter.util.Constants.USER_ROLE_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse save(@RequestBody AuthRegister authRegister) {
        return accountService.save(authRegister);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@RequestBody AuthLogin authLogin) {
        return accountService.login(authLogin);
    }

    @GetMapping("/author")
    public Author getAuthor(@RequestHeader(USER_ID_KEY) Long id,
                            @RequestHeader(USER_ROLE_KEY) Role role) {
        return accountService.getAuthor(id, role);
    }
}