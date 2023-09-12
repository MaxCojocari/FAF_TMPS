package core;

import java.util.*;

import core.interfaces.ITransactionPool;
import core.interfaces.ITransaction;

public class TransactionPool implements ITransactionPool {
    private ArrayList<ITransaction> T;
    private int maxPoolSize;

    public TransactionPool() {
        T = new ArrayList<ITransaction>();
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

    public void addTransaction(ITransaction t) {
        t.setChecked();
        if (isPoolFull())
            T.clear();
        T.add(t);
    }

    public boolean isPoolFull() {
        return T.size() >= maxPoolSize;
    }

    public ArrayList<ITransaction> getPool() {
        return T;
    }
}
