package core.transactions;

import actors.accounts.Account;
import core.transactions.interfaces.Transaction;

public abstract class BaseTransaction implements Transaction {
    private String id;
    private Account sender;
    private Account receiver;
    protected double amount;
    private double fee;
    private long timestamp;
    private boolean checked;
    private boolean isTransferDone;

    public BaseTransaction(String id, Account sender, Account receiver, double amount, double fee, long timestamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
        isTransferDone = false;
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

    public double getAmount() {
        return amount;
    }

    public void setChecked() {
        this.checked = true;
    }

    public boolean getTransferDone() {
        return isTransferDone;
    }

    public void setTransferDone() {
        isTransferDone = true;
    }
}