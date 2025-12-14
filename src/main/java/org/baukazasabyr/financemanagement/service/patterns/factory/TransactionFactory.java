package org.baukazasabyr.financemanagement.service.patterns.factory;

import org.baukazasabyr.financemanagement.model.Transaction;

public interface TransactionFactory {
    Transaction createTransaction();
}