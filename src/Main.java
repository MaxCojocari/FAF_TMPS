import java.util.*;

import actors.ExternallyOwnedAccount;
import core.Block;
import core.Blockchain;
import core.TransactionPool;
import useCases.SwapTransactionUseCase;
import useCases.TransferTransactionUseCase;

public class Main {
    static int maxNrUsers = 6;
    static int maxNrLiqPools = 10;
    static int maxNrVaults = 5;
    static int maxAmountAssetsRand = 1;
    static int maxAmountAssetsUser = 10;
    static int nrBlocks = 3;
    static int poolSize = 2;
    static int difficulty = 2;

    protected static Random random = new Random();
    private static ArrayList<ExternallyOwnedAccount> users = new ArrayList<ExternallyOwnedAccount>();
    private static ArrayList<ExternallyOwnedAccount> liquidityPools = new ArrayList<ExternallyOwnedAccount>();
    public static Queue<Object> queue = new LinkedList<Object>();

    public static TransferTransactionUseCase transferTxCase = new TransferTransactionUseCase();
    public static SwapTransactionUseCase swapTxUseCase = new SwapTransactionUseCase();

    public static void main(String[] args) {
        genUsers(users);
        genSwaps(liquidityPools);

        Block block = null;
        Blockchain blockchain = new Blockchain();
        TransactionPool TxPool = new TransactionPool();
        TxPool.setPoolSize(poolSize);

        int k = 0;
        int q = 0;
        System.out.println("Balances before");
        for (ExternallyOwnedAccount a : users) {
            System.out.println(a.getAddress());
            System.out.println(a.getBalanceETH() + " ETH");
            System.out.println(a.getBalanceUSDT() + " USDT");
        }

        System.out.println("\n");

        while (k < nrBlocks * poolSize) {
            int option = random.nextInt(2);
            if (option == 0) {
                transferTxCase.genState(
                        maxNrUsers,
                        maxAmountAssetsRand,
                        TxPool,
                        users);
            } else if (option == 1) {
                swapTxUseCase.genState(
                        maxNrUsers,
                        maxNrLiqPools,
                        maxAmountAssetsRand,
                        TxPool,
                        users,
                        liquidityPools);
            }
            k++;
            if (TxPool.isPoolFull()) {
                if (q == 0)
                    block = new Block(q++, null, TxPool.getPool());
                else
                    block = new Block(q++, block.getCurrHash(), TxPool.getPool());
                block.mineBlock(difficulty);
                blockchain.addBlock(block);
            }
        }

        blockchain.getBlocks();

        System.out.println("Balances after");

        for (ExternallyOwnedAccount a : users) {
            System.out.println(a.getAddress());
            System.out.println(a.getBalanceETH() + " ETH");
            System.out.println(a.getBalanceUSDT() + " USDT");
        }
    }

    public static void genUsers(ArrayList<ExternallyOwnedAccount> a) {
        for (int i = 0; i < maxNrUsers; ++i) {
            ExternallyOwnedAccount newUser = new ExternallyOwnedAccount(
                    getRandomAddress(40),
                    getRandomNumber(0, maxAmountAssetsUser),
                    getRandomNumber(0, maxAmountAssetsUser));
            a.add(newUser);
        }
    }

    public static void genSwaps(ArrayList<ExternallyOwnedAccount> a) {
        for (int i = 0; i < maxNrLiqPools; ++i) {
            ExternallyOwnedAccount newLiquidityPool = new ExternallyOwnedAccount(
                    getRandomAddress(40),
                    getRandomNumber(100, 100000),
                    getRandomNumber(100, 100000));
            a.add(newLiquidityPool);
        }
    }

    private static String getRandomAddress(int numchars) {
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(random.nextInt()));
        }

        return "0x" + sb.toString().substring(0, numchars);
    }

    public static double getRandomNumber(int min, int max) {
        return (Math.random() * (max - min)) + min;
    }
}
