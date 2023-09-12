package actors;

public class ExternallyOwnedAccount extends Account {
    private double balanceUSDT;

    public ExternallyOwnedAccount(String address, double balanceETH, double balanceUSDT) {
        super(address, 0, balanceETH);
        this.address = address;
        this.nonce = 0;
        this.balanceETH = balanceETH;
        this.balanceUSDT = balanceUSDT;
    }

    public String getAddress() {
        return address;
    };

    public int nonce() {
        return nonce;
    };

    public void incNonce() {
        nonce++;
    };

    public boolean sendETH(double amount, String to) {
        if (balanceETH < amount) {
            System.out.println("INSUFFICIENT_ETH_BALANCE" + "\n");
            return false;
        }
        if (amount <= 0)
            return false;
        balanceETH -= amount;
        return true;
    }

    public boolean receiveETH(double amount) {
        if (amount <= 0)
            return false;
        balanceETH += amount;
        return true;
    }

    public boolean sendUSDT(double amount, String to) {
        if (balanceUSDT < amount) {
            System.out.println("INSUFFICIENT_USDT_BALANCE" + "\n");
            return false;
        }
        if (amount <= 0)
            return false;
        balanceUSDT -= amount;
        return true;
    }

    public boolean receiveUSDT(double amount) {
        if (amount <= 0)
            return false;
        balanceUSDT += amount;
        return true;
    }

    public double getBalanceUSDT() {
        return balanceUSDT;
    }

    public double getBalanceETH() {
        return super.balanceETH;
    }
}
