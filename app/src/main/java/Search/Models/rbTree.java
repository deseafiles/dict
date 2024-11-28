package Search.Models;

import java.util.ArrayList;
import java.util.List;

public class rbTree{
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
        // System.out.println("Key " + node.getKey() + " berhasil ditambahkan!");
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

    private void printHelper(Node root, String Indent, boolean isLeft) {
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

    public String searchExactMatchKey(String key) {
        Node result = searchHelperUsingMatchKey(this.root, key);
        return (result != null) ? result.toString() : null;
    }
    
    private Node searchHelperUsingMatchKey(Node node, String key) {
        if (node == null || node == TNULL) {
            return null;
        }
    
        if (node.getKey().equals(key)) {
            System.out.println("Ditemukan value dengan key sama : " + node.getKey() + ", Value : " + node.getValue()+ "\n");
            return node;
        }
    
        Node leftResult = searchHelperUsingMatchKey(node.getLeft(), key);
        if (leftResult != null) {
            return leftResult;
        }
    
        Node rightResult = searchHelperUsingMatchKey(node.getRight(), key);
        if (rightResult != null) {
            return rightResult;
        }
    
        return null;
    }

    //jadi kalau ada key yang included (bukan sama persis), kode ini yang dipakai
    public List<Node> searchIncludedMatchKey(String key) {
        return searchHelperUsingIncludedKey(this.root, key);
    }

    private List<Node> searchHelperUsingIncludedKey(Node node, String key) {
        List<Node> resultByKey = new ArrayList<>();

        if (node == TNULL) {
            return resultByKey;
        }

        if (node.getKey().contains(key)) {
            System.out.println("Ditemukan mengandung key : " + node.getKey() + ", Value : " + node.getValue() + "\n");
            resultByKey.add(node);
        }

        resultByKey.addAll(searchHelperUsingIncludedKey(node.getLeft(), key));
        resultByKey.addAll(searchHelperUsingIncludedKey(node.getRight(), key));

        return resultByKey;
    }

     //jadi kalau ada value yang included (bukan sama persis), kode ini yang dipakai
    public List<Node> searchIncludedMatchValue(String value) {
        return searchHelperUsingIncludedValue(this.root, value);
    }

    private List<Node> searchHelperUsingIncludedValue(Node node, String value) {
        List<Node> resultbyValue = new ArrayList<>();

        if (node == TNULL){
            return resultbyValue;
        }

        if(node.getValue().contains(value)){
            System.out.println("Ditemukan value : " + node.getValue() + "\n");
            resultbyValue.add(node);
        }

        resultbyValue.addAll(searchHelperUsingIncludedValue(node.getLeft(), value));
        resultbyValue.addAll(searchHelperUsingIncludedValue(node.getRight(), value));

        return resultbyValue;
    }
}  








































    // public String searchExactMatchValue(K value){
    //     Node<K,V> node = searchHelperUsingMatchValue(this.root, value);
    //     return node.getValue().toString();
    // }

    // private Node<K,V> searchHelperUsingMatchValue(Node<K,V> node, V value){
    //     if (node == TNULL) {
    //         return TNULL;
    //     }

    //     if (node.getValue().equals(value)){
    //         System.out.println("Ditemukan Key : " + node.getKey() + ", Value : " + node.getValue());
    //         return node;
    //     }

    //     searchHelperUsingMatchValue(node.getLeft(), value);
    //     searchHelperUsingMatchValue(node.getRight(), value);
        
    //     return node;
    // }




// public String searchValueUsingKey(rbTree<String, String> tree, String key){
// Node<String, String> result = tree.search(key);

// if (result != null) {
// System.out.println("Key found: " + key + ", Value: " + result);
// } else {
// System.out.println("Key " + key + " not found in the tree.");
// }
// }

// public static void main(String[] args) {
// rbTree<String, String> tree = new rbTree<>();

// tree.insert("Alya", "valueeee");
// tree.insert("al", "semua");
// tree.insert("ai za", null);
// tree.insert("ul", null);
// tree.insert("semua", "1234");

// tree.search("a");
// tree.printTree();
// }

// public String searchValueUsingKey(rbTree<String, String> tree, String key){
// Node<String, String> result = tree.search(key);

// if (result != null) {
// System.out.println("Key found: " + key + ", Value: " + result);
// } else {
// System.out.println("Key " + key + " not found in the tree.");
// }
// }
// }
