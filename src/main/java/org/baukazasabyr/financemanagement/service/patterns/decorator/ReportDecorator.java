package org.baukazasabyr.financemanagement.service.patterns.decorator;

import org.baukazasabyr.financemanagement.model.Transaction;
import java.util.List;

public abstract class ReportDecorator implements TransactionReport {

    protected TransactionReport wrappedReport;

    public ReportDecorator(TransactionReport wrappedReport) {
        this.wrappedReport = wrappedReport;
    }

    @Override
    public List<Transaction> generateReport() {
        return wrappedReport.generateReport();
    }
}