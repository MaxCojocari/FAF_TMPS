package core.interfaces;

import java.util.ArrayList;

public interface IBlockchain {
    public void addBlock(IBlock block);

    public boolean validateBlock(IBlock lastBlock, IBlock newBlock);

    public boolean validateBlockchain();

    public ArrayList<IBlock> getBlocks();

    public IBlock getPrevBlock();

    public Iterator createIterator();
}
