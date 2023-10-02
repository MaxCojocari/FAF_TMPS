package core.interfaces;

public interface IBlock {
    public String computeHash();

    public void mineBlock(int difficulty);

    public IBlock clone();

    public int getIndex();

    public void setIndex(int index);

    public String getCurrHash();

    public String getPrevHash();

    public void setPrevHash(String prevHash);

    public long getTimestamp();
}
