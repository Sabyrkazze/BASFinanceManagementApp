package org.baukazasabyr.financemanagement.controller;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.baukazasabyr.financemanagement.service.TransactionService;
import org.baukazasabyr.financemanagement.service.patterns.decorator.CategoryReportDecorator;
import org.baukazasabyr.financemanagement.service.patterns.decorator.DateRangeReportDecorator;
import org.baukazasabyr.financemanagement.service.patterns.decorator.SimpleTransactionReport;
import org.baukazasabyr.financemanagement.service.patterns.decorator.TransactionReport;
import org.baukazasabyr.financemanagement.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String listTransactions(@RequestParam(required = false, defaultValue = "SortByDate") String sort, Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions(sort);
        model.addAttribute("transactions", transactions);
        model.addAttribute("sortType", sort);
        return "transactions";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("types", TransactionType.values());
        model.addAttribute("transaction", new Transaction() {
            @Override
            public TransactionType getType() { return null; }
        });
        return "add_transaction";
    }

    @PostMapping("/add")
    public String saveTransaction(
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam BigDecimal amount,
            @RequestParam String category,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
        transactionService.createAndSaveTransaction(transactionType, description, amount, category, date);

        return "redirect:/";
    }

    @GetMapping("/reports")
    public String getReports(
            @RequestParam(required = false, defaultValue = "All") String filterType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String category,Model model) {

        List<Transaction> reportList = transactionService.getReport(filterType, startDate, endDate, category);

        model.addAttribute("reportList", reportList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("category", category);
        model.addAttribute("filterType", filterType);

        return "reports";
    }
}