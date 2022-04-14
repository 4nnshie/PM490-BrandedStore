package com.pm490.PM490.controller;

import com.pm490.PM490.model.Account;

import com.pm490.PM490.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@PreAuthorize("hasAuthority('ADMIN')")

public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/create")
    long createBankAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/findByUserId/{userId}")
    ResponseEntity<List<Account>> get(@PathVariable Long userId) {

        // Get all accounts by user id
        List<Account> accounts = accountService.findByUserId(userId);

        // Throws not found HTTP response if there is no account
        if (accounts.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(accounts);
    }

}
