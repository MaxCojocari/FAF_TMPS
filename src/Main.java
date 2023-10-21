import actors.accounts.Account;
import core.Block;
import core.Blockchain;
import core.BlockchainService;
import core.interfaces.IBlock;
import core.transactions.TransactionPool;
import core.transactions.interfaces.Transaction;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance();
        TransactionPool transactionPool = new TransactionPool();

        // facade demo
        BlockchainService blockchainService = new BlockchainService();
        int id1 = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, "ETH", 100);
        int id2 = blockchainService.createNewAccount(blockchainService.CONTRACT_TYPE, "USDT", 100);
        Transaction tx = blockchainService.transferAssets(id1, id2, "ETH", 0.03);
        transactionPool.addTransaction(tx);
        System.out.println(tx.getInternalInfo());

        Account alice = blockchainService.getAccount(id1);
        Account smartContract = blockchainService.getAccount(id2);

        // Prototype demo
        IBlock block = new Block(0, null, transactionPool.getPool());
        block.mineBlock(2);
        blockchain.addBlock(block);

        // IBlock newBlock = block.clone();
        // newBlock.setIndex(1);
        // newBlock.setPrevHash(block.getCurrHash());
        // newBlock.mineBlock(3);
        // blockchain.addBlock(newBlock);

        Blockchain.getInstance().getBlocks();
    }
}
