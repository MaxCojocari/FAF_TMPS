package cryptography;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import core.transactions.TransferTransaction;
import core.transactions.interfaces.Transaction;
import cryptography.interfaces.Leaf;
import cryptography.interfaces.Tree;

public class MerkleTree implements Tree {
    private ArrayList<Transaction> leaves;
    private ArrayList<Leaf> leavesObjects = new ArrayList<Leaf>();

    public MerkleTree(ArrayList<Transaction> leaves) {
        this.leaves = leaves;
    }

    public boolean insert(Object leaf) {
        return leaves.add((Transaction) leaf);
    }

    public boolean remove(Object leaf) {
        return leaves.remove(leaf);
    }

    public int search(Object leaf) {
        return leaves.indexOf(leaf);
    }

    public MerkleLeaf getRoot() {
        Queue<Leaf> queue = new LinkedList<Leaf>();

        for (Transaction t : leaves) {
            queue.add(
                    new MerkleLeaf(HashGenerator.computeSha256Hash(t.getInternalInfo()), null, null));
        }

        while (queue.size() != 1) {
            Leaf leftLeaf = queue.remove();
            Leaf rightLeaf = queue.remove();
            Leaf newLeaf = new MerkleLeaf(
                    HashGenerator
                            .computeSha256Hash(((MerkleLeaf) leftLeaf).getHash() + ((MerkleLeaf) rightLeaf).getHash()),
                    leftLeaf,
                    rightLeaf);
            queue.add(newLeaf);
        }

        return (MerkleLeaf) queue.remove();
    }

    public void getLeaves() {
        for (Transaction t : leaves) {
            String l = "";
            l = ((TransferTransaction) t).getInternalInfo();
            leavesObjects.add(new MerkleLeaf(HashGenerator.computeSha256Hash(l), null, null));
        }

        if (leavesObjects.size() % 2 != 0) {
            leavesObjects.add(new MerkleLeaf(HashGenerator.computeSha256Hash(""), null, null));
        }
    }
}
