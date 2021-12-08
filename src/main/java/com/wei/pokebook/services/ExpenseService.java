package com.wei.pokebook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wei.pokebook.models.Expense;
import com.wei.pokebook.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	
	// CRUD
	// READ ALL EXPENSE
	public List<Expense> allExpense() {
		return expenseRepository.findAll();
	}
	
	// CREATE EXPENSE
	public Expense createExpense(Expense newExpense) {
		return expenseRepository.save(newExpense);
	}
	
	// READ ONE EXPENSE
	public Expense findExpense(Long id) {
		Optional<Expense> optionalExpense = expenseRepository.findById(id);
		
		if (optionalExpense.isPresent()) {
			return optionalExpense.get();
		} else {
			return null;
		}
	}
	
	// UPDATE ONE EXPENSE
	public Expense updateExpense(
			Long id,
			String name,
			String vendor,
			double amount,
			String description) {
		Expense foundExpense = findExpense(id);
		
		if (foundExpense != null) {
			foundExpense.setName(name);
			foundExpense.setVendor(vendor);
			foundExpense.setAmount(amount);
			foundExpense.setDescription(description);
			return expenseRepository.save(foundExpense);
		} else {
			return createExpense(new Expense(name, vendor, amount, description));
		}
	}
	
	// DELETE a expense
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}
	
}
