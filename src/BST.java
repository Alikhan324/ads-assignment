import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class Entry<K, V> {
        public K key;
        public V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;

        while (true) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                current.value = value;
                return;
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }
                current = current.right;
            }
        }
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                return current.value;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;

            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return;

        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.value = successor.value;

            parent = successorParent;
            current = successor;
        }

        Node child;

        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<Entry<K, V>>() {
            private Stack<Node> stack = new Stack<>();

            {
                Node current = root;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Entry<K, V> next() {
                Node node = stack.pop();

                Node current = node.right;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                return new Entry<>(node.key, node.value);
            }
        };
    }
}