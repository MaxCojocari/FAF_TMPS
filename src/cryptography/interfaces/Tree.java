package cryptography.interfaces;

public interface Tree {
    public boolean insert(Object leaf);

    public boolean remove(Object leaf);

    public int search(Object leaf);

    public void getLeaves();

    public Leaf getRoot();
}
