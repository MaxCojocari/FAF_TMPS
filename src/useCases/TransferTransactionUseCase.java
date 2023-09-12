package useCases;

import java.util.*;
import static java.util.Map.entry;

import actors.ExternallyOwnedAccount;
import core.TransactionPool;
import core.TransferTransaction;

public class TransferTransactionUseCase {
    private int i = 0;
    private int j = 0;
    protected static Random random = new Random();
    private static Map<Integer, String> assets = Map.ofEntries(entry(0, "ETH"), entry(1, "USDT"));

    public void genState(
            int maxNrUsers,
            int maxAmountAssetsRand,
            TransactionPool TxPool,
            ArrayList<ExternallyOwnedAccount> users) {
        while (i == j) {
            i = random.nextInt(maxNrUsers);
            j = random.nextInt(maxNrUsers);
        }
        ExternallyOwnedAccount user1 = users.get(i);
        ExternallyOwnedAccount user2 = users.get(j);

        String asset = assets.get(random.nextInt(2));
        double amount = Math.random() * maxAmountAssetsRand;

        TransferTransaction tx = new TransferTransaction(user1, user2, asset, amount);
        TxPool.addTransaction(tx);
        System.out.println(tx.getInternalInfo());

        System.out.println("------------------------------------------------------");
    }
}
