package sk.cybersoft.cybernotes.cybernotesspring.service;

import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.AlreadyExistsException;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;

import java.util.List;

public interface AccountService {
    List<AccountEntity> getAllAccounts();
    AccountEntity getAccountById(Long accountId) throws ResourceNotFoundException;
    AccountEntity createAccount(AccountEntity account) throws AlreadyExistsException;
    AccountEntity updateAccount(AccountEntity account, Long accountId) throws ResourceNotFoundException;
    void deleteAccount(Long accountId) throws ResourceNotFoundException;
}
