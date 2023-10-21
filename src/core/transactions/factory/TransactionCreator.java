package core.transactions.factory;

import actors.accounts.Account;
import core.transactions.interfaces.Transaction;

public abstract class TransactionCreator {

    public boolean validateTransaction(Transaction t, String currency) {
        try {
            if (t.getAmount() > t.getSender().getBalance(currency)) {
                throw new Exception("User does not possess this asset!");
            }
            return true;
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public abstract Transaction createTransaction(Account sender, Account receiver, double amount, String currency);
}
