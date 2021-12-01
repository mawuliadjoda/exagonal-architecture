package com.esprit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esprit.domain.entities.Account;
import com.esprit.infrastructure.database.AccountAdapter;
import com.esprit.infrastructure.database.AccountJpa;
import com.esprit.infrastructure.database.AccountJpaRepository;

@ExtendWith(MockitoExtension.class)
class AccountAdapterTest {

	@InjectMocks
	AccountAdapter accountAdapter;

	@Mock
	AccountJpaRepository accountJpaRepository;

	@Test
	void getAll() {
		AccountJpa accountJpa = new AccountJpa();
		long accountNumber = 12345;
		accountJpa.setAccountNumber(accountNumber);
		accountJpa.setBalance(Double.valueOf(0));
		
		List<AccountJpa> accountsJpas = List.of(accountJpa);
		
		Mockito.when(accountJpaRepository.findAll()).thenReturn(accountsJpas);
		
		
		List<Account> accounts = accountAdapter.getAll();
		assertThat(accounts).size().isEqualTo(1);
	}
	
	@Test
	void retrieveAccount() {
		AccountJpa accountJpa = new AccountJpa();
		long accountNumber = 12345;
		accountJpa.setAccountNumber(accountNumber);
		accountJpa.setBalance(Double.valueOf(0));
		Mockito.when(accountJpaRepository.getById(accountNumber)).thenReturn(accountJpa);
		
		Account account = accountAdapter.retrieve(accountNumber);
		assertThat(account).extracting(Account::getAccountNumber).isEqualTo(accountNumber);
	}

}
