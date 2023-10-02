package core.transactions.factory;

import actors.accounts.Account;
import core.transactions.TransferTransaction;
import core.transactions.interfaces.Transaction;
import cryptography.HashGenerator;

public class TransferTransactionCreator extends TransactionCreator {
    public Transaction createTransaction(Account sender, Account receiver, double amount, String currency) {
        String id = HashGenerator.computeSha1Hash(sender.getAddress() + receiver.getAddress()).substring(4, 7);
        double fee = Math.random();
        long timestamp = System.currentTimeMillis();

        return new TransferTransaction(id, sender, receiver, amount, fee, timestamp, currency);
    }
}
