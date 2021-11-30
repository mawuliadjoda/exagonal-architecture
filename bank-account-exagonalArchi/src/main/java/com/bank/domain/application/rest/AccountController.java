package com.bank.domain.application.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.domain.entities.Account;
import com.bank.domain.use_cases.AccountService;
import com.bank.domain.use_cases.BankAccountException;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/accounts")
@Api(value = "Api pour les operations sur un compte")
public class AccountController {

//	@Autowired
	private AccountService accountService;

	public AccountController() {
		super();
		accountService = new AccountService();
	}

	@PostMapping
	public void saveAccount(Account account) {
		accountService.saveAccount(account);
	}

	@GetMapping("{accountNumber}")
	public Account retrieve(@PathVariable("accountNumber") Long accountNumber) {
		return accountService.retrieveAccount(accountNumber);
	}

	@GetMapping()
	public List<Account> getAll() {
		Account account = new Account();
		account.setAccountNumber(Long.valueOf(123));
		account.setBalance(Double.valueOf(100));
		account.setDate(LocalDate.now());

		accountService.saveAccount(account);
		return accountService.getAllAccount();
	}

	@PostMapping(value = "/{accountNumber}/deposit/{depositAmount}")
	void deposit(@PathVariable Long accountNumber, @PathVariable double depositAmount) throws BankAccountException {
		accountService.deposit(accountNumber, depositAmount);
	}

	@PostMapping(value = "/{accountNumber}/withdraw/{depositAmount}")
	void withdraw(@PathVariable Long accountNumber, @PathVariable double depositAmount) throws BankAccountException {
		accountService.withdraw(accountNumber, depositAmount);
	}

}
