package com.esprit.domain.use_cases;

import org.springframework.stereotype.Component;

import com.esprit.domain.entities.Account;

@Component
public class AccountService {
	private final AccountPort accountPort;

	public AccountService(AccountPort accountPort) {
		super();
		this.accountPort = accountPort;
	}

	public Account deposit(Long accountNumber, double amount) throws BankAccountException {
		if (amount < 0) {
			throw new BankAccountException("the amount to deposit must be positive");
		}
		Account account = accountPort.retrieve(accountNumber);

		if (account.getBalance() == null) {
			account.setBalance(amount);
		}
		account.setBalance(account.getBalance() + amount);
		return accountPort.saveAccount(account);
	}

	public Account withdraw(Long accountNumber, double amount) throws BankAccountException {
		if (amount < 0) {
			throw new BankAccountException("the amount to deposit must be positive");
		}

		Account account = accountPort.retrieve(accountNumber);

		if (account.getBalance() < amount) {
			throw new BankAccountException(
					String.format("operation refused, insufficient fund for %s.", account.getAccountNumber()));
		}

		if (account.getBalance() > amount) {
			account.setBalance(account.getBalance() - amount);
		}

		return accountPort.saveAccount(account);
	}

}
