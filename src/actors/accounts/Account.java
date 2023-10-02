package actors.accounts;

import java.util.Random;

public abstract class Account implements BaseAccount {
    private String address;
    private int nonce;
    private double balanceETH;
    private double balanceUSDT;
    private String privateKey;
    private String publicKey;
    private Random random = new Random();

    public Account(String address, int nonce, double balanceETH, double balanceUSDT, String publicKey,
            String privateKey) {
        this.address = address;
        this.nonce = nonce;
        this.balanceETH = balanceETH;
        this.balanceUSDT = balanceUSDT;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    protected String getRandomAddress() {
        int numchars = 40;
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(random.nextInt()));
        }

        return "0x" + sb.toString().substring(0, numchars);
    }

    public String getAddress() {
        return address;
    }

    public int getNonce() {
        return nonce;
    }

    public double getBalanceETH() {
        return balanceETH;
    }

    public double getBalanceUSDT() {
        return balanceUSDT;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String resetAddress() {
        address = getRandomAddress();
        return address;
    }

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

    public void incNonce() {
        nonce++;
    };
}
