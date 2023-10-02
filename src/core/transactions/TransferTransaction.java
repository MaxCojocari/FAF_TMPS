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
        if (assetSymbol.equals("ETH")) {
            if (sender.sendETH(amount, assetSymbol))
                receiver.receiveETH(amount);
        } else if (assetSymbol.equals("USDT")) {
            if (sender.sendUSDT(amount, assetSymbol))
                receiver.receiveUSDT(amount);
        }
    }

    public String getInternalInfo() {
        String s = "TransferTx \n";
        s += "Sender:\t\t" + super.getSender().getAddress() + "\n";
        s += "Receiver:\t" + super.getReceiver().getAddress() + "\n";
        s += "Amount:\t\t" + amount + " " + assetSymbol + "\n";
        return s;
    }

    public double getAmount() {
        return amount;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }
}
