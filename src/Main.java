import actors.accounts.Account;
import core.Block;
import core.Blockchain;
import core.BlockchainService;
import core.interfaces.IBlock;
import core.transactions.TransactionPool;
import core.transactions.decorators.GasMetricsDecorator;
import core.transactions.decorators.ITransactionDetailsPage;
import core.transactions.decorators.MetadataDecorator;
import core.transactions.decorators.TransactionDetailsPage;
import core.transactions.interfaces.Transaction;
import core.vault.Vault;
import core.vault.VaultProxy;
import core.vault.interfaces.IVault;
import cryptography.MerkleNode;
import cryptography.MerkleTree;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance();
        TransactionPool transactionPool = new TransactionPool();

        // facade demo
        BlockchainService blockchainService = new BlockchainService();
        Integer aliceId = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, "ETH", 100);
        Integer bobId = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, "ETH", 200);
        Integer contractId = blockchainService.createNewAccount(blockchainService.CONTRACT_TYPE, "USDT", 100);

        Transaction tx = blockchainService.transferAssets(aliceId, bobId, "ETH", 0.03);
        transactionPool.addTransaction(tx);
        displayInfo(tx.getInternalInfo());

        tx = blockchainService.transferAssets(contractId, aliceId, "USDT", 20.45);
        transactionPool.addTransaction(tx);
        displayInfo(tx.getInternalInfo());

        // Decorator demo
        ITransactionDetailsPage page = new TransactionDetailsPage(tx, "Ethereum", 1.1);
        page = new GasMetricsDecorator(page, tx);
        page = new MetadataDecorator(page, tx);
        page.display();

        // Composite demo
        MerkleTree merkleTree = new MerkleTree(transactionPool.getPool());
        MerkleNode root = merkleTree.getRoot();
        displayInfo("Merkle Tree root hash: 0x" + root.getHash());

        // Proxy demo
        IVault vault = new Vault(aliceId, "ETH", blockchainService);
        VaultProxy proxy = new VaultProxy(vault, aliceId, blockchainService);
        tx = proxy.deposit(aliceId, Double.parseDouble("20"));
        transactionPool.addTransaction(tx);
        displayInfo(tx.getInternalInfo());

        proxy.addOwner(aliceId, bobId);
        tx = proxy.deposit(bobId, Double.parseDouble("10"));
        transactionPool.addTransaction(tx);
        displayInfo(tx.getInternalInfo());

        IBlock block = new Block(0, null, transactionPool.getPool());
        block.mineBlock(2);
        blockchain.addBlock(block);
        Blockchain.getInstance().getBlocks();
    }

    public static void displayInfo(String info) {
        System.out.println(info + "\n");
    }
}
