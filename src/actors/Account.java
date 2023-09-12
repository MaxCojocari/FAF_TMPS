package actors;

import java.util.Random;

public abstract class Account implements BaseAccount {
    protected String address;
    protected int nonce;
    protected double balanceETH;
    private Random random = new Random();

    public Account(String address, int nonce, double balanceETH) {
        this.address = address;
        this.nonce = nonce;
        this.balanceETH = balanceETH;
    }

    public String resetAddress() {
        address = getRandomAddress();
        return address;
    }

    private String getRandomAddress() {
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
}
