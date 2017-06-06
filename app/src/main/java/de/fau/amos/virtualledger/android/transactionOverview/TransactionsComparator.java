package de.fau.amos.virtualledger.android.transactionOverview;

import java.util.Comparator;

/**
 * Created by sebastian on 05.06.17.
 */

public class TransactionsComparator implements Comparator<Transaction> {
    @Override
    public int compare(Transaction transaction, Transaction t1) {
        return transaction.booking().getDate().compareTo(t1.booking().getDate());
    }
}