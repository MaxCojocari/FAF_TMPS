package core.transactions.decorators;

import core.transactions.interfaces.Transaction;
import cryptography.HashGenerator;

public class TransactionDetailsPage implements ITransactionDetailsPage {
    private Transaction transaction;
    private String network;
    private double htmlVersion;
    private boolean darkMode;

    public TransactionDetailsPage(Transaction transaction, String network, double htmlVersion) {
        this.transaction = transaction;
        this.network = network;
        this.htmlVersion = htmlVersion;
    }

    public void display() {
        String header = "Transaction details \n";
        header += "Type:\t\t" + transaction.getType() + "\n";
        header += "Tx Hash:\t" + getTransactionHash(transaction) + "\n";
        String baseContent = header + transaction.getInternalInfo();
        System.out.println(baseContent);
    }

    public String getNetwork() {
        return network;
    }

    public double getHtmlVersion() {
        return htmlVersion;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    private String getTransactionHash(Transaction transaction) {
        return "0x" + HashGenerator.computeSha256Hash(transaction.getInternalInfo());
    }
}
