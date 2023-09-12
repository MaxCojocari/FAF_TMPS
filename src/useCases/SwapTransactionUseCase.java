package useCases;

import java.util.*;
import static java.util.Map.entry;

import core.SwapTransaction;
import core.TransactionPool;
import actors.ExternallyOwnedAccount;

public class SwapTransactionUseCase {
    protected static Random random = new Random();
    private static Map<Integer, String> assets = Map.ofEntries(entry(0, "ETH"), entry(1, "USDT"));

    public void genState(
            int maxNrUsers,
            int maxNrLiqPools,
            int maxAmountAssetsRand,
            TransactionPool TxPool,
            ArrayList<ExternallyOwnedAccount> users,
            ArrayList<ExternallyOwnedAccount> liquidityPools) {
        ExternallyOwnedAccount user = users.get(random.nextInt(maxNrUsers));
        ExternallyOwnedAccount exchange = liquidityPools.get(random.nextInt(maxNrLiqPools));

        int iAssetIn = random.nextInt(2);
        int iAssetOut = 1 - iAssetIn;
        double amount = Math.random() * maxAmountAssetsRand;

        SwapTransaction stx = new SwapTransaction(user, exchange, assets.get(iAssetIn), assets.get(iAssetOut), amount);

        TxPool.addTransaction(stx);

        System.out.println(stx.getInternalInfo());

        System.out.println("------------------------------------------------------");
    }
}
