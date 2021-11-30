package com.bank.domain.use_cases;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bank.domain.entities.Account;
import com.bank.domain.entities.Operation;

public class OperationService {
	Map<String, Operation> map = new HashMap<>();

	public void saveOperation(Account account, String label, double amount) {
		
		Operation operation = new Operation();
		operation.setAccount(account);
		operation.setAmount(amount);
		operation.setLabel(label);
		operation.setLocalDateTime(LocalDateTime.now());
		
		map.put(String.valueOf(operation.getLocalDateTime().getNano()), operation);
	}

	public Collection<Operation> getAll() {
		return map.values();
	}
	
	public List<String> showHistory(List<Operation> operations) {
		
		
		List<String> historys = new ArrayList<String>();
		for(Operation operation: operations) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(MessageFormat.format(" \n Operation {0} Date {1} Amount {2} Balance {3}", operation.getLabel(), operation.getLocalDateTime(), operation.getAmount(), operation.getAccount().getBalance()));

			historys.add(sb.toString());
		}
		
		return historys;
	} 
}
