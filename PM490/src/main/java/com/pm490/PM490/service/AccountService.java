package com.pm490.PM490.service;

import com.pm490.PM490.model.Account;

import java.util.List;

public interface AccountService {

    /**
     * Add bank account
     * @param account Account
     * @return bank account Id
     */
    long addAccount(Account account);

    /**
     * Search accounts by userId
     * @param userId User Id
     * @return list of the accounts
     */
    List<Account> findByUserId(long userId);
}
