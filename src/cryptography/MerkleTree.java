package cryptography;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import core.transactions.interfaces.Transaction;

public class MerkleTree implements MerkleNode {
    private ArrayList<MerkleNode> leaves;

    public MerkleTree(ArrayList<Transaction> targetTxs) {
        leaves = new ArrayList<MerkleNode>();
        getLeaves(targetTxs);
    }

    public boolean insert(MerkleNode leaf) {
        return leaves.add(leaf);
    }

    public boolean remove(MerkleNode leaf) {
        return leaves.remove(leaf);
    }

    public int search(MerkleNode leaf) {
        return leaves.indexOf(leaf);
    }

    public String getHash() {
        return getRoot().getHash();
    }

    public MerkleNode getRoot() {
        Queue<MerkleNode> queue = new LinkedList<MerkleNode>();

        for (MerkleNode leaf : leaves) {
            queue.add(leaf);
        }

        while (queue.size() != 1) {
            MerkleNode leftLeaf = queue.remove();
            MerkleNode rightLeaf = queue.remove();
            MerkleNode newLeaf = new MerkleLeaf(
                    HashGenerator.computeSha256Hash(leftLeaf.getHash() + rightLeaf.getHash()),
                    leftLeaf,
                    rightLeaf);
            queue.add(newLeaf);
        }

        return queue.remove();
    }

    public void getLeaves(ArrayList<Transaction> targetTxs) {
        for (Transaction t : targetTxs) {
            String l = "";
            l = t.getInternalInfo();
            leaves.add(new MerkleLeaf(HashGenerator.computeSha256Hash(l), null, null));
        }

        if (leaves.size() % 2 != 0) {
            leaves.add(new MerkleLeaf(HashGenerator.computeSha256Hash(""), null, null));
        }
    }
}
