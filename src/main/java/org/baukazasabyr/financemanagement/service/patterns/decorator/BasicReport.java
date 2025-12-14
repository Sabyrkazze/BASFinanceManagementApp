package org.baukazasabyr.financemanagement.service.patterns.decorator;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.baukazasabyr.financemanagement.repository.TransactionRepository;
import java.util.List;

public class BasicReport implements TransactionReport {

    private final TransactionRepository repository;

    public BasicReport(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transaction> generateReport() {
        return repository.findAll();
    }
}