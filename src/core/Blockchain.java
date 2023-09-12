package core;

import java.util.ArrayList;

import core.interfaces.IBlockchain;

public class Blockchain implements IBlockchain {
    private ArrayList<Block> blockchain;

    public Blockchain() {
        blockchain = new ArrayList<Block>();
    }

    public void addBlock(Block block) {
        if (blockchain.size() == 0) {
            if (validateBlock(null, block))
                blockchain.add(block);
        } else {
            Block lastBlock = getPrevBlock();
            if (validateBlock(lastBlock, block))
                blockchain.add(block);
        }
    }

    public boolean validateBlock(Block lastBlock, Block newBlock) {
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
        for (Block b : blockchain) {
            System.out.println(b.toString());
        }
    }

    public Block getPrevBlock() {
        return blockchain.get(blockchain.size() - 1);
    }
}