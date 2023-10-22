package actors.accounts;

import java.util.Hashtable;
import java.util.Random;

public class Account {
    private String address;
    private int nonce;
    private String privateKey;
    private String publicKey;
    private Hashtable<String, Double> balances;
    private Random random = new Random();

    public Account(String address, int nonce, String publicKey, String privateKey) {
        this.address = address;
        this.nonce = nonce;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.balances = new Hashtable<String, Double>();
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

    public double getBalance(String currency) {
        return balances.get(currency);
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

    public boolean sendAssets(String currency, double amount, String to) {
        Double currentBalance = balances.get(currency);
        if (currentBalance == null) {
            currentBalance = 0.;
        }
        if (currentBalance < amount) {
            System.out.println("INSUFFICIENT_" + currency + "_BALANCE" + "\n");
            return false;
        }
        if (amount <= 0)
            return false;

        currentBalance -= amount;
        balances.put(currency, currentBalance);

        return true;
    }

    public boolean receiveAssets(String currency, double amount) {
        if (amount <= 0)
            return false;
        Double currentBalance = balances.get(currency);
        if (currentBalance == null) {
            currentBalance = 0.;
        }
        balances.put(currency, currentBalance + amount);
        return true;
    }

    public void incNonce() {
        nonce++;
    };
}
