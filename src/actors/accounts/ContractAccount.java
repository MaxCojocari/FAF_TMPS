package actors.accounts;

public class ContractAccount extends Account {
    public String storageHash;
    public String codeHash;

    public ContractAccount(
            String address,
            String storageHash,
            String codeHash,
            String privateKey,
            String publicKey) {
        super(address, 0, privateKey, publicKey);
        this.storageHash = storageHash;
        this.codeHash = codeHash;
    }

    public String getStorageHash() {
        return storageHash;
    }

    public String getCodeHash() {
        return codeHash;
    }

    public void sendNativeTokens(double amount, String to) {
        super.sendAssets("ETH", amount, to);
    }
}
