package core.transactions;

import actors.accounts.Account;

public class TransferTransaction extends BaseTransaction {
    private String assetSymbol;

    public TransferTransaction(
            String id,
            Account sender,
            Account receiver,
            double amount,
            double fee,
            long timestamp,
            String assetSymbol) {
        super(id, sender, receiver, amount, fee, timestamp);
        this.assetSymbol = assetSymbol;
        transferFrom(assetSymbol, amount, sender, receiver);
    }

    public void transferFrom(String assetSymbol, double amount, Account sender, Account receiver) {
        assert !getTransferDone();

        if (sender.sendAssets(assetSymbol, amount, receiver.getAddress()))
            receiver.receiveAssets(assetSymbol, amount);

        setTransferDone();
    }

    public String getInternalInfo() {
        String s = "Sender:\t\t" + super.getSender().getAddress() + "\n";
        s += "Receiver:\t" + super.getReceiver().getAddress() + "\n";
        s += "Amount:\t\t" + super.getAmount() + " " + assetSymbol;
        return s;
    }

    public double getAmount() {
        return amount;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public String getType() {
        return "currency transfer";
    }
}
