package com.esprit.infrastructure.database;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class AccountJpa {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	Long accountNumber;
	Double balance;
	LocalDate date;
}
