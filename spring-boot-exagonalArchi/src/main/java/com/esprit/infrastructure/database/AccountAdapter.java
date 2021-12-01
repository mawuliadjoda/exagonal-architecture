package com.esprit.infrastructure.database;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esprit.domain.entities.Account;
import com.esprit.domain.use_cases.AccountPort;

@Component
public class AccountAdapter implements AccountPort {
	@Autowired
	private AccountJpaRepository repository;

	@Override
	public Account saveAccount(Account account) {
		AccountJpa accountJpa = repository.save(toAccountJpa(account));
		return toAccount(accountJpa);
	}

	@Override
	public Account retrieve(Long accountNumber) {
		return toAccount(repository.getById(accountNumber));
	}

	@Override
	public List<Account> getAll() {
		return repository.findAll().stream().map(this::toAccount).collect(Collectors.toList());
	}

	private AccountJpa toAccountJpa(Account account) {
		AccountJpa accountJpa = new AccountJpa();
		accountJpa.setAccountNumber(account.getAccountNumber());
		accountJpa.setBalance(account.getBalance());
		accountJpa.setDate(account.getDate());

		return accountJpa;
	}

	private Account toAccount(AccountJpa accountJpa) {
		Account account = new Account();
		account.setAccountNumber(accountJpa.getAccountNumber());
		account.setBalance(accountJpa.getBalance());
		account.setDate(accountJpa.getDate());

		return account;
	}

}
