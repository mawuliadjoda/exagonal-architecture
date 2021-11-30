package com.bank;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.bank.domain.entities.Account;
import com.bank.domain.use_cases.AccountService;
import com.bank.domain.use_cases.BankAccountException;
import com.bank.domain.use_cases.OperationService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountTesting {
	public static Account account;
	public static AccountService accountService;
	public OperationService operationService;

	@BeforeAll
	static void setup() {
		accountService = new AccountService();
		account = new Account();

		long accountNumber = 123;

		account.setAccountNumber(accountNumber);
		account.setBalance(0);
		accountService.saveAccount(account);
	}

	@Test
	@Order(1)
	void when_deposit_amount_shouldPositive_account() throws BankAccountException {
		double amount = 100;
		long accountNumber = 123;

		accountService.deposit(accountNumber, amount);

		Account result = accountService.retrieveAccount(accountNumber);
		assertThat(result.getBalance()).isEqualTo(amount);
	}

	@Test
	@Order(2)
	void when_withdraw_amount_should_positive() throws BankAccountException {
		double amount = 75;
		long accountNumber = 123;

		accountService.withdraw(accountNumber, amount);
		Account result = accountService.retrieveAccount(accountNumber);
		assertThat(result.getBalance()).isEqualTo(25);
	}

	@Test
	@Order(3)
	void getAllHistories() {
		// accountService.showHistory().stream().forEach(System.out::println);
		List<String> operations = accountService.showHistory();
		assertThat(operations).isNotEmpty();
	}
}
