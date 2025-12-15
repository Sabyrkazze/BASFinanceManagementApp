package org.baukazasabyr.financemanagement.service.patterns.decorator;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.baukazasabyr.financemanagement.repository.TransactionRepository;
import java.util.List;

public class CategoryReportDecorator extends ReportDecorator {

    private final String category;
    private final TransactionRepository repository;

    public CategoryReportDecorator(TransactionReport wrappedReport, String category, TransactionRepository repository) {
        super(wrappedReport);
        this.category = category;
        this.repository = repository;
    }

    @Override
    public List<Transaction> generateReport() {
        return repository.findByCategory(category);
    }
}