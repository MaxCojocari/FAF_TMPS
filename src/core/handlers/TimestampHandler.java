package core.handlers;

import core.interfaces.IBlock;

public class TimestampHandler extends Handler {
    public boolean check(IBlock lastBlock, IBlock newBlock) {
        if (lastBlock == null) {
            return checkNext(lastBlock, newBlock);
        }
        return lastBlock.getTimestamp() < newBlock.getTimestamp() && checkNext(lastBlock, newBlock);
    }
}
