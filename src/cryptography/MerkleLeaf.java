package cryptography;

import cryptography.interfaces.Leaf;

public class MerkleLeaf implements Leaf {
  private String hash;
  private Leaf rightChild;
  private Leaf leftChild;

  public MerkleLeaf(String hash, Leaf rightChild, Leaf leftChild) {
    this.hash = hash;
    this.rightChild = rightChild;
    this.leftChild = leftChild;
  }

  public String getHash() {
    return hash;
  }

  public Leaf leftChild() {
    return leftChild;
  }

  public Leaf rightChild() {
    return rightChild;
  }
}
