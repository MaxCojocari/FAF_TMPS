package cryptography;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import core.Transaction;
import core.TransferTransaction;
import core.interfaces.ITransaction;
import cryptography.interfaces.Leaf;
import cryptography.interfaces.Tree;

public class MerkleTree implements Tree {
    private ArrayList<ITransaction> leaves;
    private ArrayList<Leaf> leavesObjects = new ArrayList<Leaf>();

    public MerkleTree(ArrayList<ITransaction> leaves) {
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

        for (ITransaction t : leaves) {
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
        for (ITransaction t : leaves) {
            String l = "";
            l = ((TransferTransaction) t).transactionInfo();
            leavesObjects.add(new MerkleLeaf(HashGenerator.computeSha256Hash(l), null, null));
        }

        if (leavesObjects.size() % 2 != 0) {
            leavesObjects.add(new MerkleLeaf(HashGenerator.computeSha256Hash(""), null, null));
        }
    }
}
