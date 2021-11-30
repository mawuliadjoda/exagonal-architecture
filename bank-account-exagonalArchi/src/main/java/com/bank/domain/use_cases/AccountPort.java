package com.bank.domain.use_cases;

import java.util.List;

import com.bank.domain.entities.Account;

public interface AccountPort {
	void deposit(long accountNumber, double amount);

	void withdraw(long accountNumber, double amount);

	Account retrieveAccount(long accountNumber);

	void saveAccount(Account account);

	List<String> showHistory();
	
	List<Account> getAllAccount();
}
