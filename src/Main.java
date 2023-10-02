import actors.AccountDirector;
import actors.accounts.ContractAccount;
import actors.accounts.ExternallyOwnedAccount;
import actors.builders.ContractAccountBuilder;
import actors.builders.ExternallyOwnerAccountBuilder;
import core.Block;
import core.Blockchain;
import core.interfaces.IBlock;
import core.transactions.TransactionPool;
import core.transactions.factory.SwapTransactionCreator;
import core.transactions.factory.TransactionCreator;
import core.transactions.factory.TransferTransactionCreator;
import core.transactions.interfaces.Transaction;

public class Main {
    public static void main(String[] args) {
        // Singleton demo
        Blockchain blockchain = Blockchain.getInstance();
        TransactionPool transactionPool = new TransactionPool();

        // Builder demo
        AccountDirector director = new AccountDirector();

        ExternallyOwnerAccountBuilder externallyOwnedAccountBuilder = new ExternallyOwnerAccountBuilder();
        director.constructExternallyOwnerAccount(externallyOwnedAccountBuilder);
        ExternallyOwnedAccount alice = externallyOwnedAccountBuilder.getResult();

        ContractAccountBuilder builder = new ContractAccountBuilder();
        director.constructExternallyOwnerAccount(builder);
        ContractAccount smartContract = builder.getResult();

        // Factory Method demo
        TransactionCreator transferTxCreator = new TransferTransactionCreator();
        Transaction tx1 = transferTxCreator.createTransaction(smartContract, alice, 0.00123, "ETH");
        transactionPool.addTransaction(tx1);
        System.out.println(tx1.getInternalInfo());

        TransactionCreator swapTxCreator = new SwapTransactionCreator();
        Transaction tx2 = swapTxCreator.createTransaction(alice, smartContract, 0.03, "USDT");
        transactionPool.addTransaction(tx1);
        System.out.println(tx2.getInternalInfo());

        // Prototype demo
        IBlock block = new Block(0, null, transactionPool.getPool());
        block.mineBlock(2);
        blockchain.addBlock(block);

        IBlock newBlock = block.clone();
        newBlock.setIndex(1);
        newBlock.setPrevHash(block.getCurrHash());
        newBlock.mineBlock(3);
        blockchain.addBlock(newBlock);

        System.out.println("Singleton Correctness Proof Comparison:");
        System.out.println("---------------------------------------------------");
        blockchain.getBlocks();
        System.out.println("---------------------------------------------------");
        Blockchain.getInstance().getBlocks();
    }
}
