package core;

import java.util.ArrayList;

import core.interfaces.IBlock;
import core.interfaces.IBlockchain;

public final class Blockchain implements IBlockchain {
    private static Blockchain instance;
    private static ArrayList<IBlock> blockchain;

    private Blockchain() {
        blockchain = new ArrayList<IBlock>();
    }

    public static Blockchain getInstance() {
        if (blockchain == null) {
            instance = new Blockchain();
        }
        return instance;
    }

    public void addBlock(IBlock block) {
        if (blockchain.size() == 0) {
            if (validateBlock(null, block))
                blockchain.add(block);
        } else {
            IBlock lastBlock = getPrevBlock();
            if (validateBlock(lastBlock, block))
                blockchain.add(block);
        }
    }

    public boolean validateBlock(IBlock lastBlock, IBlock newBlock) {
        if (lastBlock == null) {
            return newBlock.getIndex() == 0 &&
                    newBlock.getPrevHash() == null &&
                    newBlock.computeHash().equals(newBlock.getCurrHash());
        }

        return lastBlock.getCurrHash().equals(newBlock.getPrevHash()) &&
                lastBlock.getTimestamp() < newBlock.getTimestamp() &&
                lastBlock.getIndex() + 1 == newBlock.getIndex() &&
                newBlock.computeHash().equals(newBlock.getCurrHash());
    }

    public boolean validateBlockchain() {
        for (int i = 0; i < blockchain.size() - 1; ++i) {
            if (!validateBlock(blockchain.get(i), blockchain.get(i + 1)))
                return false;
        }
        return true;
    }

    public void getBlocks() {
        for (IBlock b : blockchain) {
            System.out.println(b.toString());
        }
    }

    public IBlock getPrevBlock() {
        return blockchain.get(blockchain.size() - 1);
    }
}