package org.baukazasabyr.financemanagement.model;

import org.baukazasabyr.financemanagement.util.TransactionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Transaction {

    @Override
    public TransactionType getType() {
        return TransactionType.EXPENSE;
    }
}