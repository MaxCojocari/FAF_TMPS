package actors.builders;

import actors.accounts.ExternallyOwnedAccount;

public class ExternallyOwnerAccountBuilder implements AccountBuilder {
    private String address;
    private double balanceETH;
    private double balanceUSDT;
    private String privateKey;
    private String publicKey;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBalanceETH(double balanceETH) {
        this.balanceETH = balanceETH;
    }

    public void setBalanceUSDT(double balanceUSDT) {
        this.balanceUSDT = balanceUSDT;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public ExternallyOwnedAccount getResult() {
        return new ExternallyOwnedAccount(address, balanceETH, balanceUSDT, privateKey, publicKey);
    }

}
