package core.interfaces;

import core.Block;

public interface IBlockchain {
    public void addBlock(Block block);

    public boolean validateBlock(Block lastBlock, Block newBlock);

    public boolean validateBlockchain();

    public void getBlocks();

    public Object getPrevBlock();
}
