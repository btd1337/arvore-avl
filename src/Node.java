public class Node {
    private int higher;
    private int value;
    private Node left;
    private Node right;

    Node(int value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

    public int getHigher() {
        return this.higher;
    }
    
    public void setHigher(int higher) {
        this.higher = higher;
    }
    
    public Node getRight() {
        return this.right;
    }
    
    public void setRight(Node node) {
        this.right = node;
    }

    public Node getLeft() {
        return this.left;
    }
    
    public void setLeft(Node node) {
        this.left = node;
    }

    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }  

}