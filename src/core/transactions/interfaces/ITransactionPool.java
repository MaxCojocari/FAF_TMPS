package core.transactions.interfaces;

import java.util.ArrayList;

public interface ITransactionPool {
    public void setPoolSize(int size);

    public Object getTransaction(int i);

    public int getNrTransactions();

    public void addTransaction(Transaction t);

    public boolean isPoolFull();

    public ArrayList<Transaction> getPool();
}
