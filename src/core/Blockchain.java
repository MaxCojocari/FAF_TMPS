package core;

import java.util.ArrayList;

import core.handlers.Handler;
import core.handlers.HashHandler;
import core.handlers.IndexHandler;
import core.handlers.TimestampHandler;
import core.interfaces.IBlock;
import core.interfaces.IBlockchain;
import core.interfaces.Iterator;

public final class Blockchain implements IBlockchain {
    private static Blockchain instance;
    private static ArrayList<IBlock> blockchain;
    private Handler blockHandler;

    private Blockchain() {
        blockchain = new ArrayList<IBlock>();
        blockHandler = Handler.link(new Handler[] { new IndexHandler(), new TimestampHandler(), new HashHandler() });
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
        return blockHandler.check(lastBlock, newBlock);
    }

    public boolean validateBlockchain() {
        for (int i = 0; i < blockchain.size() - 1; ++i) {
            if (!validateBlock(blockchain.get(i), blockchain.get(i + 1)))
                return false;
        }
        return true;
    }

    public ArrayList<IBlock> getBlocks() {
        return blockchain;
    }

    public IBlock getPrevBlock() {
        return blockchain.get(blockchain.size() - 1);
    }

    public Iterator createIterator() {
        return new BlockchainIterator(this);
    }
}