package com.esprit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.domain.entities.Account;
import com.esprit.infrastructure.database.AccountJpa;
import com.esprit.infrastructure.database.AccountJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountJpaRepositoryTest {

	@Autowired
	AccountJpaRepository accountJpaRepository;

	@Test
	void find_all_should_return_record() {
		AccountJpa account = new AccountJpa();
		long accountNumber = 12345;
		account.setAccountNumber(accountNumber);
		account.setBalance(Double.valueOf(500));
		
		accountJpaRepository.save(account);
		
		Assertions.assertThat(accountJpaRepository.findAll()).size().isEqualTo(1);
	}

}
