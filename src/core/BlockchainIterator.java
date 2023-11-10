package core;

import java.util.ArrayList;

import core.interfaces.IBlock;
import core.interfaces.IBlockchain;
import core.interfaces.Iterator;

public class BlockchainIterator implements Iterator {
    private IBlockchain blockchain;
    private ArrayList<IBlock> blocks;
    private int currentPosition = 0;

    public BlockchainIterator(IBlockchain blockchain) {
        this.blockchain = blockchain;
    }

    public IBlock getNextBlock() {
        if (!hasMore()) {
            return null;
        }
        return blocks.get(currentPosition++);
    }

    public boolean hasMore() {
        lazyLoad();
        return currentPosition < blocks.size();
    }

    public void reset() {
        currentPosition = 0;
    }

    private void lazyLoad() {
        if (blocks == null || blocks.size() == 0) {
            blocks = blockchain.getBlocks();
        }
    }
}
