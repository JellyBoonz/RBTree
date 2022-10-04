public class Main {
    public static void main(String[] args) {
        RBTree myTree = new RBTree(26);
        myTree.rbInsert(new RBNode(3));
        myTree.rbInsert(new RBNode(7));
        myTree.rbInsert(new RBNode(10));
        myTree.rbInsert(new RBNode(12));
        myTree.rbInsert(new RBNode(14));
        myTree.rbInsert(new RBNode(15));
        myTree.rbInsert(new RBNode(16));
        myTree.rbInsert(new RBNode(17));
        myTree.rbInsert(new RBNode(19));
        myTree.rbInsert(new RBNode(20));
        myTree.rbInsert(new RBNode(21));
        myTree.rbInsert(new RBNode(23));
        myTree.rbInsert(new RBNode(28));
        myTree.rbInsert(new RBNode(30));
        myTree.rbInsert(new RBNode(35));
        myTree.rbInsert(new RBNode(38));
        myTree.rbInsert(new RBNode(39));
        myTree.rbInsert(new RBNode(41));
        myTree.rbInsert(new RBNode(47));
//


        myTree.drawTree(myTree.root, "", false);

    }
}