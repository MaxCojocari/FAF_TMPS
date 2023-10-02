package core.interfaces;

public interface IBlockchain {
    public void addBlock(IBlock block);

    public boolean validateBlock(IBlock lastBlock, IBlock newBlock);

    public boolean validateBlockchain();

    public void getBlocks();

    public Object getPrevBlock();
}
