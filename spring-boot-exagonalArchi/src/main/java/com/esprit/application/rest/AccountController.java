package com.esprit.application.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.domain.entities.Account;
import com.esprit.domain.infrastructure.database.AccountAdapter;
import com.esprit.domain.use_cases.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
//	private AccountAdapter accountAdapter;
	private AccountService accountService;

	@PostMapping
	void saveAccount(Account account) {
		accountService.saveAccount(account);
	}

	@GetMapping("{accountNumber}")
	Account retrieve(@PathVariable("accountNumber") Long accountNumber) {
		return accountService.retrieve(accountNumber);
	}

	@GetMapping()
	List<Account> getAll() {
		Account account = new Account();
		account.setAccountNumber(Long.valueOf(123));
		account.setBalance(Double.valueOf(100));
		account.setDate(LocalDate.now());

		accountService.saveAccount(account);

		return accountService.getAll();
	}

	@PostMapping(value = "/{accountNumber}/deposit/{depositAmount}")
	void deposit(@PathVariable Long accountNumber, @PathVariable double depositAmount) {
		accountService.deposit(accountNumber, depositAmount);
	}

	@PostMapping(value = "/{accountNumber}/withdraw/{depositAmount}")
	void withdraw(@PathVariable Long accountNumber, @PathVariable double depositAmount) {
		accountService.withdraw(accountNumber, depositAmount);
	}
}
