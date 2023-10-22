package core.vault;

import java.util.Hashtable;

import core.BlockchainService;
import core.transactions.interfaces.Transaction;
import core.vault.interfaces.IVault;

public class VaultProxy implements IVault {
    private IVault vault;
    private BlockchainService blockchainService;
    private Hashtable<String, Boolean> owners;

    public VaultProxy(IVault vault, Integer vaultOwnerId, BlockchainService blockchainService) {
        this.vault = vault;
        this.blockchainService = blockchainService;
        this.owners = new Hashtable<String, Boolean>();
        String vaultOwnerAddr = blockchainService.getAccount(vaultOwnerId).getAddress();
        owners.put(vaultOwnerAddr, true);
    }

    public boolean checkAccess(Integer accountId) {
        Boolean isOwner = owners.get(blockchainService.getAccount(accountId).getAddress());
        if (isOwner == null || !isOwner) {
            System.out.println("Access prohibited!");
            return false;
        }
        return true;
    }

    public Transaction deposit(Integer depositorId, Double amount) {
        if (checkAccess(depositorId)) {
            return vault.deposit(depositorId, amount);
        }
        return null;
    }

    public Transaction withdraw(Integer withdrawerId, Double amount) {
        if (checkAccess(withdrawerId)) {
            return vault.withdraw(withdrawerId, amount);
        }
        return null;
    }

    public void setFreezed(Integer requesterId, boolean mode) {
        if (checkAccess(requesterId)) {
            vault.setFreezed(requesterId, mode);
        }
    }

    public void getAmountDepositied(Integer requesterId) {
        if (checkAccess(requesterId)) {
            vault.getAmountDepositied(requesterId);
        }
    }

    public void addOwner(Integer requesterId, Integer newOwnerId) {
        if (checkAccess(requesterId)) {
            String newOwnerAddr = blockchainService.getAccount(newOwnerId).getAddress();
            owners.put(newOwnerAddr, true);
        }
    }
}
