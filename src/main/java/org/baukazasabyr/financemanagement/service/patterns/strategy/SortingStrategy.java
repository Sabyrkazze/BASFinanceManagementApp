package org.baukazasabyr.financemanagement.service.patterns.strategy;

import org.baukazasabyr.financemanagement.model.Transaction;
import java.util.List;

public interface SortingStrategy {
    List<Transaction> sort(List<Transaction> transactions);
}