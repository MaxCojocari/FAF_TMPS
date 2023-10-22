package core;

import java.util.Hashtable;

import actors.AccountDirector;
import actors.accounts.Account;
import actors.builders.ContractAccountBuilder;
import actors.builders.ExternallyOwnedAccountBuilder;
import core.transactions.factory.TransactionCreator;
import core.transactions.factory.TransferTransactionCreator;
import core.transactions.interfaces.Transaction;

public class BlockchainService {
    public final String EXTERNALLY_OWNED_TYPE = "EXTERNALLY_OWNED";
    public final String CONTRACT_TYPE = "CONTRACT";
    private int nonce;
    private Hashtable<Integer, Account> accounts;

    public BlockchainService() {
        this.nonce = 0;
        this.accounts = new Hashtable<Integer, Account>();
    }

    public Account getAccount(int id) {
        return accounts.get(id);
    }

    public int createNewAccount(String type, String currency, double initialAmount) {
        AccountDirector director = new AccountDirector();
        Account account = null;

        switch (type) {
        case EXTERNALLY_OWNED_TYPE:
            ExternallyOwnedAccountBuilder externallyOwnedAccountBuilder = new ExternallyOwnedAccountBuilder();
            director.constructExternallyOwnedAccount(externallyOwnedAccountBuilder);
            account = externallyOwnedAccountBuilder.getResult();
            break;

        case CONTRACT_TYPE:
            ContractAccountBuilder contractAccountBuilder = new ContractAccountBuilder();
            director.constructExternallyOwnedAccount(contractAccountBuilder);
            account = contractAccountBuilder.getResult();
            break;

        default:
            System.out.println("Invalid account type");
            break;
        }

        if (account != null) {
            accounts.put(nonce, account);
            account.receiveAssets(currency, initialAmount);
        }

        return nonce++;
    }

    public Transaction transferAssets(int fromId, int toId, String currency, double amount) {
        Account sender = accounts.get(fromId);
        Account receiver = accounts.get(toId);
        TransactionCreator transferTxCreator = new TransferTransactionCreator();
        return transferTxCreator.createTransaction(sender, receiver, amount, currency);
    }
}
