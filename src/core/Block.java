package core;

import java.util.ArrayList;

import core.interfaces.IBlock;
import core.transactions.interfaces.Transaction;
import cryptography.HashGenerator;
import cryptography.MerkleTree;

public class Block implements IBlock {
    private int index;
    private long timestamp;
    private String currHash;
    private String prevHash;
    private String merkleRoot;
    private ArrayList<Transaction> transactions;
    private int nonce;

    public Block(int index, String prevHash, ArrayList<Transaction> transactions) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.prevHash = prevHash;
        this.transactions = transactions;
        this.merkleRoot = (new MerkleTree(transactions)).getHash();
        currHash = computeHash();
    }

    public String computeHash() {
        String input = index + timestamp + prevHash + nonce;
        for (Transaction t : transactions)
            input += t.getInternalInfo();
        return HashGenerator.computeSha256Hash(input);
    }

    public void mineBlock(int difficulty) {
        nonce = 0;
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!currHash.substring(0, difficulty).equals(target)) {
            nonce++;
            currHash = computeHash();
        }
    }

    public String toString() {
        String s = "Block # " + index + "\n";
        s = s + "PreviousHash:\t" + prevHash + "\n";
        s = s + "Timestamp:\t" + timestamp + "\n";
        s = s + "Nonce:\t\t" + nonce + "\n";
        s = s + "CurrentHash:\t" + currHash + "\n";
        s = s + "Merkle root:\t" + merkleRoot + "\n";
        return s;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCurrHash() {
        return currHash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    };

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }

    public IBlock clone() {
        return new Block(this.index, this.prevHash, this.transactions);
    }
}
