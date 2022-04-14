package com.pm490.PM490.service.implementation;

import com.pm490.PM490.model.Account;
import com.pm490.PM490.repository.AccountRepository;
import com.pm490.PM490.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public long addAccount(Account account) {
        Account addedAccount = accountRepository.save(account);
        //...
        return addedAccount.getId();
    }

    @Override
    public List<Account> findByUserId(long userId) {
        List<Account> accounts = accountRepository.findByUserId(userId);
        //...
        return accounts;
    }

}
