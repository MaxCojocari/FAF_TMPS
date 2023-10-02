package actors.builders;

public interface AccountBuilder {
    public void setAddress(String address);

    public void setBalanceETH(double balanceETH);

    public void setBalanceUSDT(double balanceUSDT);

    public void setPrivateKey(String privateKey);

    public void setPublicKey(String publicKey);
}
