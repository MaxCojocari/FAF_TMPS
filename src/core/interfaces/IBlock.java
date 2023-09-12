package core.interfaces;

public interface IBlock {
    public String computeHash();

    public void mineBlock(int difficulty);

}
