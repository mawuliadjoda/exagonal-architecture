package com.bank.domain.use_cases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bank.domain.entities.Account;

@Component
public class AccountService implements AccountPort {

	private OperationService operationService;

	public AccountService() {
		super();
		this.map = new HashMap<Long, Account>();
		this.operationService = new OperationService();
	}

	public void saveAccount(Account account) {
		map.put(account.getAccountNumber(), account);
	}

	public void deposit(long accountNumber, double amount) {
		Account account = retrieveAccount(accountNumber);
		account.setBalance(account.getBalance() + amount);

		saveAccount(account);
		operationService.saveOperation(account, DEPOSIT, amount);
	}

	public Account retrieveAccount(long accountNumber) {
		return map.get(accountNumber);
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = retrieveAccount(accountNumber);
		account.setBalance(account.getBalance() - amount);

		saveAccount(account);

		operationService.saveOperation(account, WITHDRAW, amount);
	}
	
	public List<Account> getAllAccount() {
		return map.values().stream().collect(Collectors.toList());
	}

	public List<String> showHistory() {
		return operationService.showHistory(operationService.getAll().stream().collect(Collectors.toList()));
	}

	private static final String WITHDRAW = "WITHDRAW";

	private static final String DEPOSIT = "DEPOSIT";

	private Map<Long, Account> map;

}