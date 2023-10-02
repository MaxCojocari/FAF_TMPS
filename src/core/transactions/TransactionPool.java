package core.transactions;

import java.util.*;

import core.transactions.interfaces.ITransactionPool;
import core.transactions.interfaces.Transaction;

public class TransactionPool implements ITransactionPool {
    private ArrayList<Transaction> T;
    private int maxPoolSize;

    public TransactionPool() {
        T = new ArrayList<Transaction>();
    }

    public void setPoolSize(int size) {
        maxPoolSize = size;
    }

    public Object getTransaction(int i) {
        return T.get(i);
    }

    public int getNrTransactions() {
        return T.size();
    }

    public void addTransaction(Transaction t) {
        t.setChecked();
        if (isPoolFull())
            T.clear();
        T.add(t);
    }

    public boolean isPoolFull() {
        return T.size() >= maxPoolSize;
    }

    public ArrayList<Transaction> getPool() {
        return T;
    }
}
