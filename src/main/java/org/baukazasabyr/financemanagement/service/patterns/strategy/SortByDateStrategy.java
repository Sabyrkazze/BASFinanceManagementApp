package org.baukazasabyr.financemanagement.service.patterns.strategy;

import org.baukazasabyr.financemanagement.model.Transaction;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;

@Component
public class SortByDateStrategy implements SortingStrategy {

    @Override
    public List<Transaction> sort(List<Transaction> transactions) {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        return transactions;
    }
}