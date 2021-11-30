package com.esprit.domain.use_cases;

import java.util.List;

import org.springframework.stereotype.Component;

import com.esprit.domain.entities.Account;

@Component
public class AccountService {
	private final AccountPort accountPort;

	public AccountService(AccountPort accountPort) {
		super();
		this.accountPort = accountPort;
	}

	public void deposit(Long accountNumber, double amount) {
		accountPort.deposit(accountNumber, amount);
	}

	public void withdraw(Long accountNumber, double amount) {
		accountPort.withdraw(accountNumber, amount);
	}

	public void saveAccount(Account account) {
		accountPort.saveAccount(account);
	}

	public Account retrieve(Long accountNumber) {
		return accountPort.retrieve(accountNumber);
	}
	public List<Account> getAll() {
		return accountPort.getAll();
	}
}
