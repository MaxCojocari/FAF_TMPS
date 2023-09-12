package core;

import actors.Account;
import core.interfaces.ITransaction;
import cryptography.HashGenerator;

public abstract class Transaction implements ITransaction {
    private String id;
    private Account sender;
    private Account receiver;
    private double fee;
    private long timestamp;
    private boolean checked;

    public Transaction(Account sender, Account receiver) {
        this.sender = sender;
        this.receiver = receiver;
        id = HashGenerator.computeSha1Hash(sender.getAddress()).substring(2, 5);
        fee = Math.random();
        timestamp = System.currentTimeMillis();
    }

    public String getInternalInfo() {
        String s = "Transaction \n";
        s += "Sender:\t\t" + sender.getAddress() + "\n";
        s += "Receiver:\t" + receiver.getAddress() + "\n";
        return s;
    }

    public double computeFee() {
        return fee;
    }

    public String getId() {
        return id;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean getIsChecked() {
        return checked;
    }

    public void setChecked() {
        this.checked = true;
    }
}