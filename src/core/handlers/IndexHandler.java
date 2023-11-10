package core.handlers;

import core.interfaces.IBlock;

public class IndexHandler extends Handler {
    public boolean check(IBlock lastBlock, IBlock newBlock) {
        if (lastBlock == null) {
            return newBlock.getIndex() == 0 && checkNext(lastBlock, newBlock);
        }

        return lastBlock.getIndex() + 1 == newBlock.getIndex() && checkNext(lastBlock, newBlock);
    }
}
