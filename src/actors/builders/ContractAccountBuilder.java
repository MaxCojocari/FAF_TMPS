package actors.builders;

import actors.accounts.ContractAccount;

public class ContractAccountBuilder implements AccountBuilder {
    private String address;
    private double balanceETH;
    private double balanceUSDT;
    private String storageHash;
    private String codeHash;
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

    public void setStorageHash(String storageHash) {
        this.storageHash = storageHash;
    }

    public void setCodeHash(String codeHash) {
        this.codeHash = codeHash;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public ContractAccount getResult() {
        return new ContractAccount(address, balanceETH, balanceUSDT, storageHash, codeHash, privateKey, publicKey);
    }
}
