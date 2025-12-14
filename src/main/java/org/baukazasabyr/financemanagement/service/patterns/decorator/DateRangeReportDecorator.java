package org.baukazasabyr.financemanagement.service.patterns.decorator;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.baukazasabyr.financemanagement.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;

public class DateRangeReportDecorator extends ReportDecorator {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final TransactionRepository repository;

    public DateRangeReportDecorator(TransactionReport wrappedReport, LocalDate startDate, LocalDate endDate, TransactionRepository repository) {
        super(wrappedReport);
        this.startDate = startDate;
        this.endDate = endDate;
        this.repository = repository;
    }

    @Override
    public List<Transaction> generateReport() {
        return repository.findByDateBetween(startDate, endDate);
    }
}