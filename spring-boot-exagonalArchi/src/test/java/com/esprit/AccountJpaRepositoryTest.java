package com.esprit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.infrastructure.database.AccountJpaRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountJpaRepositoryTest {

	@Autowired
	AccountJpaRepository accountJpaRepository;

	@Test
	void test() {
		accountJpaRepository.findAll();
	}

}
