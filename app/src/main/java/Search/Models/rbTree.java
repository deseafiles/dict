package Search.Models;

import java.util.ArrayList;
import java.util.List;
import Search.Utils.GameGimmick;
import Search.Utils.GimmickAction;

public class rbTree {
    private Node root;
    private Node TNULL;

    public rbTree() {
        TNULL = new Node(null, null);
        TNULL.setRed(false);
        TNULL.setLeft(TNULL);
        TNULL.setRight(TNULL);
        root = TNULL;
    }

    private void fixInsert(Node node) {
        Node uncle;
        while (node.getParent() != null && node.getParent().isRed()) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                uncle = node.getParent().getParent().getRight();
                if (uncle.isRed()) {
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rightRotate(node.getParent().getParent());
                }
            } else {
                uncle = node.getParent().getParent().getLeft();
                if (uncle.isRed()) {
                    uncle.setRed(false);
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(node);
                    }
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    leftRotate(node.getParent().getParent());
                }
            }
            if (node == root) {
                break;
            }
        }
        root.setRed(false);
    }

    public void insert(String key, String value) {
        Node node = new Node(key, value);
        node.setParent(null);
        node.setKey(key);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setRed(true);
        node.setValue(value);

        Node y = null;
        Node x = root;

        while (x != TNULL) {
            y = x;
            int comparison = node.getKey().compareTo(x.getKey());
            if (comparison < 0) {
                x = x.getLeft();
            } else if (comparison > 0) {
                x = x.getRight();
            } else {
                // System.out.println("Duplicate key: " + node.getKey());
                return;
            }
        }

        node.setParent(y); // Menetapkan parent node
        if (y == null) {
            root = node; // root hanya akan di-set ketika node pertama dimasukkan
        } else {
            int comparison = node.getKey().compareTo(y.getKey());
            if (comparison < 0) {
                y.setLeft(node);
            } else {
                y.setRight(node);
            }
        }
        fixInsert(node);
    }

    private void leftRotate(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != TNULL) {
            y.getLeft().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != TNULL) {
            x.getRight().setParent(y);
        }
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            root = x;
        } else if (y == y.getParent().getLeft()) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    public List<Node> search(String key) {
        List<Node> result = searchHelper(this.root, key);
        GimmickAction action = new GameGimmick();
        
        // Check if the result list is not empty
        if (!result.isEmpty()) {
            for (Node node : result) {
                if (node.getValue().equals("gimmick")) {
                    action.gimmick(key);
                } else {
                    // Return or process the value as needed
                    System.out.println(node.getValue());
                }
            }
        }
        
        return result; // Return the list of found nodes
    }

    private List<Node> searchHelper(Node node, String key) {
        List<Node> result = new ArrayList<>();

        if (node == TNULL) {
            return result;
        }

        if (node.getKey().toString().toLowerCase().contains(key.toString().toLowerCase()) || node.getValue().toString().toLowerCase().contains(key.toString().toLowerCase())) {
            result.add(node);
        }

        result.addAll(searchHelper(node.getLeft(), key));
        result.addAll(searchHelper(node.getRight(), key));

        return result;
    }
}


