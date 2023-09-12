package core.interfaces;

public interface ITransaction {
    public String getInternalInfo();

    public Object getSender();

    public Object getReceiver();

    public boolean getIsChecked();

    public void setChecked();
}
