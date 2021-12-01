package com.esprit.domain.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Account {
	Long accountNumber;
	Double balance;
	LocalDate date;
}
