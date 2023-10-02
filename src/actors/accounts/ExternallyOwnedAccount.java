package actors.accounts;

public class ExternallyOwnedAccount extends Account {
    public ExternallyOwnedAccount(String address, double balanceETH, double balanceUSDT, String privateKey,
            String publicKey) {
        super(address, 0, balanceETH, balanceUSDT, privateKey, publicKey);
    }
}
