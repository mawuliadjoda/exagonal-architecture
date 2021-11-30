package com.esprit.domain.use_cases;

import java.util.List;

import com.esprit.domain.entities.Account;

public interface AccountPort {
	void deposit(Long accountNumber, double amount);

	void withdraw(Long accountNumber, double amount);

	void saveAccount(Account account);

	Account retrieve(Long accountNumber);
	
	List<Account> getAll();
}
