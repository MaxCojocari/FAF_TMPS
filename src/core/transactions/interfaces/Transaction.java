package core.transactions.interfaces;

import actors.accounts.Account;

public interface Transaction {
    public void transferFrom(String assetSymbol, double amount, Account sender, Account receiver);

    public double computeFee();

    public String getInternalInfo();

    public Account getSender();

    public Account getReceiver();

    public double getAmount();

    public long getTimestamp();

    public void setChecked();

    public String getType();
}
