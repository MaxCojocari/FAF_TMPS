package cryptography;

public class MerkleLeaf implements MerkleNode {
    private String hash;
    private MerkleNode rightChild;
    private MerkleNode leftChild;

    public MerkleLeaf(String hash, MerkleNode rightChild, MerkleNode leftChild) {
        this.hash = hash;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }

    public String getHash() {
        return hash;
    }

    public MerkleNode leftChild() {
        return leftChild;
    }

    public MerkleNode rightChild() {
        return rightChild;
    }
}
