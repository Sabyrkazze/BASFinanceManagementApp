package org.baukazasabyr.financemanagement.service.patterns.factory;

import org.baukazasabyr.financemanagement.model.Income;
import org.baukazasabyr.financemanagement.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class IncomeTransactionFactory implements TransactionFactory {

    @Override
    public Transaction createTransaction() {
        return new Income();
    }
}