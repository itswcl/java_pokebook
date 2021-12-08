package com.wei.pokebook.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
	
//  if on the different route we can make the get to have expense attribute	
//	@GetMapping("/pokebook/new")
//	public String newBook(@ModelAttribute("expense") Expense expense) {
//		return "new.jsp";
//	}
	
	@PostMapping("/pokebook")
	public String createExpense(
			@Valid
			@ModelAttribute("expense") Expense expense,
			BindingResult result,
			// in order for use pass data if input failed
			Model model
			) {
		if (result.hasErrors()) {
			
			List<Expense> expenses = expenseService.allExpense();
			model.addAttribute("expenses", expenses);
			
			return "/pokebook/index.jsp";
		} else {
			expenseService.createExpense(expense);
			return "redirect:/pokebook";
		}
	}
}
