package core.transactions.factory;

import actors.accounts.Account;
import core.transactions.SwapTransaction;
import core.transactions.interfaces.Transaction;
import cryptography.HashGenerator;

public class SwapTransactionCreator extends TransactionCreator {
    public Transaction createTransaction(Account sender, Account exchange, double amount, String currencyIn) {
        String id = HashGenerator.computeSha1Hash(sender.getAddress() + exchange.getAddress()).substring(2, 5);
        double fee = Math.random();
        long timestamp = System.currentTimeMillis();
        String currencyOut = currencyIn.equals("ETH") ? "USDT" : "ETH";

        return new SwapTransaction(id, sender, exchange, fee, timestamp, currencyIn, currencyOut, amount, 0.5, 0.65);
    }
}
