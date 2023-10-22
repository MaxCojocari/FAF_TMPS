package core.vault;

import java.util.Hashtable;

import core.BlockchainService;
import core.transactions.interfaces.Transaction;
import core.vault.interfaces.IVault;

public class Vault implements IVault {
    private Hashtable<String, Double> shares;
    private String currency;
    private Boolean isFreezed;
    private BlockchainService blockchainService;
    private Integer vaultId;

    public Vault(Integer ownerId, String currency, BlockchainService blockchainService) {
        this.currency = currency;
        this.isFreezed = false;
        this.blockchainService = blockchainService;
        this.shares = new Hashtable<String, Double>();
        this.vaultId = blockchainService.createNewAccount(blockchainService.EXTERNALLY_OWNED_TYPE, currency, 0);
    }

    public Transaction deposit(Integer depositorId, Double amount) {
        String accountAddress = blockchainService.getAccount(depositorId).getAddress();
        Double currentAmount = shares.get(accountAddress);
        if (currentAmount == null) {
            currentAmount = 0.;
        }
        shares.put(accountAddress, currentAmount + amount);
        Transaction tx = blockchainService.transferAssets(depositorId, vaultId, currency, amount);
        System.out.println("Account " + accountAddress + " deposited " + amount.toString() + " " + currency);
        return tx;
    };

    public Transaction withdraw(Integer withdrawerId, Double amount) {
        String accountAddress = blockchainService.getAccount(withdrawerId).getAddress();
        Double currentAmount = shares.get(accountAddress);
        if (currentAmount == null) {
            System.out.println("Account " + accountAddress + " does not have deposited!");
            return null;
        }
        if (currentAmount < amount) {
            System.out.println("Amount " + amount + " exceeds the actual deposited amount!");
            return null;
        }
        shares.put(accountAddress, currentAmount - amount);
        Transaction tx = blockchainService.transferAssets(vaultId, withdrawerId, currency, amount);
        System.out.println("Account " + accountAddress + " withdrawed " + amount.toString() + " " + currency);
        return tx;
    };

    public String getCurrency() {
        return currency;
    }

    public void getAmountDepositied(Integer requesterId) {
        String accountAddress = blockchainService.getAccount(requesterId).getAddress();
        Double currentAmount = shares.get(accountAddress);

        Double totalShares = 0.;
        for (Double share : shares.values()) {
            totalShares += share;
        }
        Double sharePercentage = currentAmount / totalShares * 100;
        System.out.println("Your desposit: " + currentAmount.toString() + " " + currency);
        System.out.println("Your share (in %): " + sharePercentage.toString());
    }

    public void setFreezed(Integer requesterId, boolean mode) {
        String accountAddress = blockchainService.getAccount(requesterId).getAddress();
        isFreezed = mode;
        String status = isFreezed ? "freezed" : "active";
        System.out.println("Account " + accountAddress + " changed vault status to " + status);
    }

    public Boolean isFreezed() {
        return isFreezed;
    }
}
