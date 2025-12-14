package org.baukazasabyr.financemanagement.service.patterns.factory;

import org.baukazasabyr.financemanagement.model.Expense;
import org.baukazasabyr.financemanagement.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class ExpenseTransactionFactory implements TransactionFactory {

    @Override
    public Transaction createTransaction() {
        return new Expense();
    }
}