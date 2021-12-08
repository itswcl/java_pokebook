package com.wei.pokebook.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wei.pokebook.models.Expense;
import com.wei.pokebook.services.ExpenseService;

@RestController
public class ExpenseApi {
	private final ExpenseService expenseService;

	public ExpenseApi(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}
	
	@GetMapping("/api/expense")
	public List<Expense> index() {
		return expenseService.allExpense();
	}

	// create one expense
	@PostMapping("/api/expense")
	public Expense create(
			@RequestParam(value="name") String name,
			@RequestParam(value="vendor") String vendor,
			@RequestParam(value="amount") double amount,
			@RequestParam(value="description") String desc
			) {
		Expense expense = new Expense(name, vendor, amount, desc);
		return expenseService.createExpense(expense);
	}
	
	// see one expense
	@GetMapping("/api/expense/{id}")
	public Expense showOne(@PathVariable("id") Long id) {
		Expense expense = expenseService.findExpense(id);
		return expense;
	}
	
	// update one expense
	@PutMapping("/api/books{id}")
	public Expense update(
			@PathVariable("id") Long id,
			@RequestParam(value="name") String name,
			@RequestParam(value="vendor") String vendor,
			@RequestParam(value="amount") double amount,
			@RequestParam(value="description") String desc
			) {
		Expense updatedExpense = expenseService.updateExpense(id, name, vendor, amount, desc);
		return updatedExpense;
	}
	
	@DeleteMapping("/api/books/{id}")
	public void deleteExpense(@PathVariable("id") Long id) {
		expenseService.deleteExpense(id);
	}
	
}
