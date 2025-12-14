package org.baukazasabyr.financemanagement.service;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.baukazasabyr.financemanagement.repository.TransactionRepository;
import org.baukazasabyr.financemanagement.service.patterns.decorator.DateRangeReportDecorator;
import org.baukazasabyr.financemanagement.service.patterns.decorator.SimpleTransactionReport;
import org.baukazasabyr.financemanagement.service.patterns.decorator.TransactionReport;
import org.baukazasabyr.financemanagement.service.patterns.factory.TransactionFactory;
import org.baukazasabyr.financemanagement.service.patterns.strategy.SortingStrategy;
import org.baukazasabyr.financemanagement.util.TransactionType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    // Spring барлық Factory және Strategy кластарын осы карталарға (Map) жинайды
    private final Map<String, TransactionFactory> factoryMap;
    private final Map<String, SortingStrategy> strategyMap;

    public TransactionService(TransactionRepository repository,
                              Map<String, TransactionFactory> factoryMap,
                              Map<String, SortingStrategy> strategyMap) {
        this.repository = repository;
        this.factoryMap = factoryMap;
        this.strategyMap = strategyMap;
    }

    public List<Transaction> getAllTransactions(String sortStrategyName) {
        List<Transaction> transactions = repository.findAll();

        String strategyBeanName = Character.toLowerCase(sortStrategyName.charAt(0)) + sortStrategyName.substring(1) + "Strategy";

        SortingStrategy strategy = strategyMap.get(strategyBeanName);

        if (strategy != null) {
            return strategy.sort(transactions);
        }

        return transactions;
    }

    public void createAndSaveTransaction(TransactionType type, String description, BigDecimal amount, String category, LocalDate date) {
        String factoryName = type.name().toLowerCase() + "TransactionFactory";

        TransactionFactory factory = factoryMap.get(factoryName);

        if (factory == null) {
            throw new IllegalArgumentException("Unknown transaction type: " + type);
        }


        Transaction transaction = factory.createTransaction();


        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setDate(date);


        repository.save(transaction);
    }

    public List<Transaction> getReport(String filterType, LocalDate startDate, LocalDate endDate) {
        TransactionReport report = new SimpleTransactionReport(repository);

        if ("DateRange".equals(filterType) && startDate != null && endDate != null) {
            report = new DateRangeReportDecorator(report, startDate, endDate, repository);
        }

        return report.generateReport();
    }
}