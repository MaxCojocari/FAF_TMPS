package core.transactions.factory;

import actors.accounts.Account;
import core.transactions.interfaces.Transaction;

public abstract class TransactionCreator {

    public boolean validateTransaction(Transaction t, String currency) {
        try {
            if (currency.equals("ETH")) {
                return t.getAmount() > t.getSender().getBalanceETH();
            } else if (currency.equals("USDT")) {
                return t.getAmount() > t.getSender().getBalanceUSDT();
            }
            throw new Exception("User does not posses this asset!");
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public abstract Transaction createTransaction(Account sender, Account receiver, double amount, String currency);
}
