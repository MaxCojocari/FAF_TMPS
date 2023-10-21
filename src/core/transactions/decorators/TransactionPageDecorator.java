package core.transactions.decorators;

import core.transactions.interfaces.Transaction;

public abstract class TransactionPageDecorator implements ITransactionDetailsPage {
    protected ITransactionDetailsPage page;
    protected Transaction transaction;

    public TransactionPageDecorator(ITransactionDetailsPage page, Transaction transaction) {
        this.page = page;
        this.transaction = transaction;
    }

    public void display() {
        page.display();
    }
}
