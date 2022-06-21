package sk.cybersoft.cybernotes.cybernotesspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import sk.cybersoft.cybernotes.cybernotesspring.entity.AccountEntity;
import sk.cybersoft.cybernotes.cybernotesspring.exception.AlreadyExistsException;
import sk.cybersoft.cybernotes.cybernotesspring.exception.ResourceNotFoundException;
import sk.cybersoft.cybernotes.cybernotesspring.repository.AccountRepository;

import java.util.List;
//TODO javaDoc
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountEntity getAccountById(Long accountId) throws ResourceNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: '" + accountId + "' not found!"));
    }

    @Override
    public AccountEntity createAccount(AccountEntity account) throws AlreadyExistsException {
        AccountEntity probe = new AccountEntity();
        probe.setUsername(account.getUsername());

        if (accountRepository.count(Example.of(probe)) > 0) {
            throw new AlreadyExistsException("User with username: '" + account.getUsername() + "' already exists!");
        }
        return accountRepository.save(account);
    }

    @Override
    public AccountEntity updateAccount(AccountEntity account, Long accountId) throws ResourceNotFoundException {
        AccountEntity userToUpdate = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: '" + accountId + "' not found!"));

        if(account.getUsername() != null && !account.getUsername().trim().isEmpty()) {
            userToUpdate.setUsername(account.getUsername());
        }
        if(account.getPassword() != null && !account.getPassword().trim().isEmpty()) {
            userToUpdate.setPassword(account.getPassword());
        }

        return accountRepository.save(userToUpdate);
    }

    @Override
    public void deleteAccount(Long accountId) throws ResourceNotFoundException {
        AccountEntity user = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: '" + accountId + "' not found!"));
        accountRepository.delete(user);
    }
}
