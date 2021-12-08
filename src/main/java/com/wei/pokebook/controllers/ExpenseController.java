package com.wei.pokebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wei.pokebook.models.Expense;
import com.wei.pokebook.services.ExpenseService;

@Controller
public class ExpenseController {
	@Autowired
	ExpenseService expenseService;
	
	
	@GetMapping("/")
	public String index() {
		return "redirect:/pokebook";
	}
	
//  if on the different route we can make the get to have expense attribute	
//	@GetMapping("/pokebook/new")
//	public String newBook(@ModelAttribute("expense") Expense expense) {
//		return "new.jsp";
//	}

	@GetMapping("/pokebook")
	public String displayAll(
			Model model,
			// to have mode attribute expense for POST request use 
			// if on the same page
			@ModelAttribute("expense") Expense expense) {
		
		List<Expense> expenses = expenseService.allExpense();
		
		model.addAttribute("expenses", expenses);
		return "/pokebook/index.jsp";
	}
	
	@PostMapping("/pokebook")
	public String createExpense(
			@Valid
			@ModelAttribute("expense") Expense expense,
			BindingResult result,
			// in order for use pass data if input failed
			Model model
			) {
		if (result.hasErrors()) {
			// in order for use pass data if input failed
			// need the model to bring out the data for us
			List<Expense> expenses = expenseService.allExpense();
			model.addAttribute("expenses", expenses);
			
			return "/pokebook/index.jsp";
		} else {
			expenseService.createExpense(expense);
			return "redirect:/pokebook";
		}
	}
	
	// route for display the info in able to edit
	@GetMapping("/pokebook/{expenseId}/edit")
	public String editRoute(
			@PathVariable("expenseId") Long expenseId,
			Model model
			) {
		Expense expense = expenseService.findExpense(expenseId);
		model.addAttribute("expense", expense);
		return "/pokebook/edit.jsp";
	}
	
	// route for update the info edited
	@PutMapping("/pokebook/{expenseId}")
	public String editRequest(
			@Valid @ModelAttribute("expense") Expense expense, BindingResult result,
			@PathVariable("expenseId") Long expenseId,
			@RequestParam(value="name") String name,
			@RequestParam(value="vendor") String vendor,
			@RequestParam(value="amount") double amount,
			@RequestParam(value="description") String description
			) {
		
		if (result.hasErrors()) {
			return "/pokebook/edit.jsp";
		} else {
			expenseService.updateExpense(expenseId, name, vendor, amount, description);
			return "redirect:/pokebook";
		}
	}
	
	@DeleteMapping("/pokebook/{expenseId}")
	public String deleteExpense(
			@PathVariable("expenseId") Long expenseId) {
			expenseService.deleteExpense(expenseId);
			return "redirect:/pokebook";
	}
	
	
	@GetMapping("/pokebook/{expenseId}")
	public String displayOne(
			@PathVariable("expenseId") Long expenseId,
			Model model) {
		
		Expense expense = expenseService.findExpense(expenseId);
		
		model.addAttribute("expense", expense);
		return "/pokebook/show.jsp";
	}
	
}
