package core.interfaces;

import java.util.ArrayList;

public interface ITransactionPool {
    public void setPoolSize(int size);

    public Object getTransaction(int i);

    public int getNrTransactions();

    public void addTransaction(ITransaction t);

    public boolean isPoolFull();

    public ArrayList<ITransaction> getPool();
}
