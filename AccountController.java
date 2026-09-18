package com.sece.eceb.eceController;

import java.util.List;

import com.sece.eceb.dto.Account;
import com.sece.eceb.service.AccountService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @GetMapping("/account")
    public List<Account> getAccount() {
        return accountService.findAll();
    }

    @GetMapping("/account/{phoneNumber}")
    public Account getOneAccount(@PathVariable String phoneNumber) {
        return accountService.findById(phoneNumber).orElse(null);
    }

    @PutMapping("/account/{phoneNumber}")
    public Account updateAccount(@PathVariable String phoneNumber,
                                 @RequestBody Account newAccount) {

        Account account = accountService.findById(phoneNumber).orElse(null);

        if (account != null) {
            account.setName(newAccount.getName());
            return accountService.save(account);
        }

        return null;
    }

    @DeleteMapping("/account/{phoneNumber}")
    public String deleteAccount(@PathVariable String phoneNumber) {

        if (accountService.existsById(phoneNumber)) {
            accountService.deleteById(phoneNumber);
            return "Account deleted successfully";
        }

        return "Account not found";
    }
}