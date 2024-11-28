package Search.Models;

public class Node {
    public String key;
    public String value;
    private Node right;
    private Node left;
    private Node parent;
    private boolean red;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.red = true;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getParent() {
        return parent;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRed() {
        return red;
    }
}

