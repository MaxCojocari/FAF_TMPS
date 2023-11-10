package core.handlers;

import core.interfaces.IBlock;

public class HashHandler extends Handler {
    public boolean check(IBlock lastBlock, IBlock newBlock) {
        boolean validHashFingerprint;

        if (lastBlock == null) {
            validHashFingerprint = newBlock.getPrevHash() == null && newBlock.computeHash().equals(newBlock.getCurrHash());
            return validHashFingerprint && checkNext(lastBlock, newBlock);
        }

        validHashFingerprint = lastBlock.getCurrHash().equals(newBlock.getPrevHash())
                && newBlock.computeHash().equals(newBlock.getCurrHash());

        return validHashFingerprint && checkNext(lastBlock, newBlock);
    }
}
