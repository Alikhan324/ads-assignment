public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(1, "One");
        tree.put(4, "Four");

        System.out.println(tree.get(3));
        System.out.println("Size: " + tree.size());

        tree.delete(3);

        for (BST.Entry<Integer, String> entry : tree) {
            System.out.println("Key: " + entry.key + ", Value: " + entry.value);
        }
    }
}