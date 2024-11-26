package Search.Models;

import org.checkerframework.checker.units.qual.K;

public class Node <K extends Comparable<K>, V> {
    private K key;
    public V value;
    private Node<K, V> right;
    private Node<K, V> left;
    private Node<K, V> parent;
    private boolean red;

    public Node() {
        // Default constructor for TNULL
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.red = true;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public Node<K, V> getParent() {
        return parent;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isRed() {
        return red;
    }
}

