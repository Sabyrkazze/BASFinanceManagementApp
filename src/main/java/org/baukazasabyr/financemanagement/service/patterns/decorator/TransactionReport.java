package org.baukazasabyr.financemanagement.service.patterns.decorator;

import org.baukazasabyr.financemanagement.model.Transaction;
import java.util.List;

public interface TransactionReport {
    List<Transaction> generateReport();
}