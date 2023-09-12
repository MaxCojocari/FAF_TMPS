package actors;

public interface BaseAccount {
    public abstract String resetAddress();

    public abstract int getNonce();

    public abstract void incNonce();
}
