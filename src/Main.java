import core.Block;
import core.Blockchain;
import core.BlockchainService;
import core.interfaces.IBlock;
import core.interfaces.Iterator;
import core.observers.AssetTransferObserver;
import core.observers.NewAccountObserver;
import core.observers.Observer;
import core.transactions.TransactionPool;
import core.transactions.interfaces.Transaction;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance();
        TransactionPool transactionPool = new TransactionPool();

        BlockchainService blockchainService = new BlockchainService();
        Observer frontEnd = new NewAccountObserver();
        Observer backEnd = new AssetTransferObserver("src/transfers.txt");
        blockchainService.attach("createNewAccount", frontEnd);
        blockchainService.attach("transferAssets", backEnd);

        Integer aliceId = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, "ETH", 100);
        Integer bobId = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, "ETH", 200);
        Integer contractId = blockchainService.createNewAccount(blockchainService.CONTRACT_TYPE, "USDT", 100);

        String prevHash = null;
        int index = 0;

        for (int i = 0; i < 2; ++i) {
            Transaction tx = blockchainService.transferAssets(aliceId, bobId, "ETH", 0.03);
            transactionPool.addTransaction(tx);
            displayInfo(tx.getInternalInfo());

            tx = blockchainService.transferAssets(contractId, aliceId, "USDT", 20.45);
            transactionPool.addTransaction(tx);
            displayInfo(tx.getInternalInfo());

            IBlock block = new Block(index++, prevHash, transactionPool.getPool());
            block.mineBlock(2);
            blockchain.addBlock(block);

            prevHash = block.getCurrHash();
        }

        Iterator blockchainIterator = blockchain.createIterator();
        while (blockchainIterator.hasMore()) {
            System.out.println(blockchainIterator.getNextBlock());
        }
    }

    public static void displayInfo(String info) {
        System.out.println(info + "\n");
    }
}
