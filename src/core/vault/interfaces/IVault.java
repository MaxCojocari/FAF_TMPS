package core.vault.interfaces;

import core.transactions.interfaces.Transaction;

public interface IVault {
    public Transaction deposit(Integer depositorId, Double amount);

    public Transaction withdraw(Integer withdrawerId, Double amount);

    public void setFreezed(Integer requesterId, boolean mode);

    public void getAmountDepositied(Integer requesterId);
}
