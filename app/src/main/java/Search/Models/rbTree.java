package Search.Models;

import java.util.ArrayList;
import java.util.List;


class Node<K extends Comparable<K>, V> {
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

    public boolean isRed() {
        return red;
    }
}

public class rbTree<K extends Comparable<K>, V> {
    private Node<K, V> root;
    private Node<K, V> TNULL;

    public rbTree() {
        TNULL = new Node<K, V>();
        TNULL.setRed(false);
        TNULL.setLeft(TNULL);
        TNULL.setRight(TNULL);
        root = TNULL;
    }

    private void fixInsert(Node<K, V> node) {
        Node<K, V> uncle;
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

    public void insert(K key, V value) {
        Node<K,V> node = new Node<>();
        node.setParent(null);
        node.setKey(key);
        node.setLeft(TNULL);
        node.setRight(TNULL);
        node.setRed(true);

        Node<K, V> y = null;
        Node<K, V> x = root;

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
        // System.out.println("Key " + node.getKey() + " berhasil ditambahkan!");
        fixInsert(node);
    }

    private void leftRotate(Node<K, V> x) {
        Node<K, V> y = x.getRight();
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

    private void rightRotate(Node<K, V> y) {
        Node<K, V> x = y.getLeft();
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

    // private void fixDelete(Node x) {
    // Node s;
    // while (x != root && !x.isRed()) {
    // if (x == x.getParent().getLeft()) {
    // s = x.getParent().getRight();
    // if (s.isRed()) {
    // s.setRed(false);
    // x.getParent().setRed(true);
    // leftRotate(x.getParent());
    // s = x.getParent().getRight();
    // }

    // if (!s.getLeft().isRed() && !s.getRight().isRed()) {
    // s.setRed(true);
    // x = x.getParent();
    // } else {
    // if (!s.getRight().isRed()) {
    // s.getLeft().setRed(false);
    // s.setRed(true);
    // rightRotate(s);
    // s = x.getParent().getRight();
    // }

    // s.setRed(x.getParent().isRed());
    // x.getParent().setRed(false);
    // s.getRight().setRed(false);
    // leftRotate(x.getParent());
    // x = root;
    // }
    // } else {
    // s = x.getParent().getLeft();
    // if (s.isRed()) {
    // s.setRed(false);
    // x.getParent().setRed(true);
    // rightRotate(x.getParent());
    // s = x.getParent().getLeft();
    // }

    // if (!s.getRight().isRed() && !s.getLeft().isRed()) {
    // s.setRed(true);
    // x = x.getParent();
    // } else {
    // if (!s.getLeft().isRed()) {
    // s.getRight().setRed(false);
    // s.setRed(true);
    // leftRotate(s);
    // s = x.getParent().getLeft();
    // }

    // s.setRed(x.getParent().isRed());
    // x.getParent().setRed(false);
    // s.getLeft().setRed(false);
    // rightRotate(x.getParent());
    // x = root;
    // }
    // }
    // }
    // x.setRed(false);
    // }

    // private void rbTransplant(Node u, Node v) {
    // if (u.getParent() == null) {
    // root = v;
    // } else if (u == u.getParent().getLeft()) {
    // u.getParent().setLeft(v);
    // } else {
    // u.getParent().setRight(v);
    // }
    // v.setParent(u.getParent());
    // }

    // private Node minimum(Node node) {
    // while (node.getLeft() != TNULL) {
    // node = node.getLeft();
    // }
    // return node;
    // }

    // public void deleteNode(char key) {
    // Node z = TNULL;
    // Node x, y;
    // Node node = root;

    // while (node != TNULL) {
    // if (node.getKey() == key) {
    // z = node;
    // }
    // if (node.getKey() <= key) {
    // node = node.getRight();
    // } else {
    // node = node.getLeft();
    // }
    // }

    // if (z == TNULL) {
    // System.out.println("Key tidak ditemukan dalam tree");
    // return;
    // }

    // y = z;
    // boolean yOriginalColor = y.isRed();
    // if (z.getLeft() == TNULL) {
    // x = z.getRight();
    // rbTransplant(z, z.getRight());
    // } else if (z.getRight() == TNULL) {
    // x = z.getLeft();
    // rbTransplant(z, z.getLeft());
    // } else {
    // y = minimum(z.getRight());
    // yOriginalColor = y.isRed();
    // x = y.getRight();
    // if (y.getParent() == z) {
    // x.setParent(y);
    // } else {
    // rbTransplant(y, y.getRight());
    // y.setRight(z.getRight());
    // y.getRight().setParent(y);
    // }
    // rbTransplant(z, y);
    // y.setLeft(z.getLeft());
    // y.getLeft().setParent(y);
    // y.setRed(z.isRed());
    // }
    // if (!yOriginalColor) {
    // fixDelete(x);
    // }
    // }

    public void printTree() {
        printHelper(this.root, " ", true);
    }

    private void printHelper(Node<K, V> root, String Indent, boolean isLeft) {
        if (root != TNULL) {

            printHelper(root.getRight(), Indent + (isLeft ? "│   " : "    "), false);
            System.out.println(Indent + (isLeft ? "└── " : "┌── ") +
                    (root.isRed() ? "\033[38;2;255;0;0m" + root.getKey() + "\033[0m" : root.getKey() + ""));
            printHelper(root.getLeft(), Indent + (isLeft ? "    " : "│   "), true);
        }
    }

    
    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.getKey() + " ");
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getRight());
        }
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.getLeft());
            System.out.print(node.getKey() + " ");
            inOrderHelper(node.getRight());
        }
    }


    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.getLeft());
            postOrderHelper(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    public void preorder() {
        System.out.print("Pre Order: ");
        preOrderHelper(this.root);
        System.out.println();
    }

    public void inorder() {
        System.out.print("In Order: ");
        inOrderHelper(this.root);
        System.out.println();
    }

    public void postorder() {
        System.out.print("Post Order: ");
        postOrderHelper(this.root);
        System.out.println();
    }

    public List<Node<K, V>> search(K key) {
        return searchHelper(this.root, key);  
    }
    
    private List<Node<K, V>> searchHelper(Node<K, V> node, K key) {
        List<Node<K, V>> result = new ArrayList<>();  
    
        if (node == TNULL) {
            return result;  
        }
    
        if (node.getKey().toString().contains(key.toString())) {
            System.out.println("Ditemukan: " + node.getKey()); 
            result.add(node); 
        }
    
        int comparison = key.compareTo(node.getKey());  
    
        if (comparison < 0) {
            result.addAll(searchHelper(node.getLeft(), key)); 
            result.addAll(searchHelper(node.getRight(), key));
        } else{
            result.addAll(searchHelper(node.getRight(), key));
            result.addAll(searchHelper(node.getLeft(), key)); 
        } 
        return result; 
    }
    

    // public String searchValueUsingKey(rbTree<String, String> tree, String key){
    // Node<String, String> result = tree.search(key);

    // if (result != null) {
    // System.out.println("Key found: " + key + ", Value: " + result);
    // } else {
    // System.out.println("Key " + key + " not found in the tree.");
    // }
    // }


//     public static void main(String[] args) {
//         rbTree<String, String> tree = new rbTree<>();

//         tree.insert("Alya", "valueeee");
//         tree.insert("al", "semua");
//         tree.insert("ai za", null);
//         tree.insert("ul", null);
//         tree.insert("semua", "1234");

//         tree.search("a");
//         tree.printTree();
//     }

    // public static void main(String []args){
    //     rbTree<Integer, String> tree = new rbTree<>();

    //     tree.insert(10);
    //     tree.insert(20);
    //     tree.insert(30);
        
    //     tree.printTree();
    // }

}
