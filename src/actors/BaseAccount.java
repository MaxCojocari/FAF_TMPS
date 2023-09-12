package actors;

public interface BaseAccount {
  public abstract String resetAddress();

  public abstract int nonce();

  public abstract void incNonce();
}
