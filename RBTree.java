class RBNode {
    static final int RED = 0;
    static final int BLACK = 1;

    private int key;
    private int color;
    private RBNode left;
    private RBNode right;
    private RBNode parent;

    RBNode() {
    }

    RBNode(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }
}

public class RBTree {
    RBNode root;
    RBNode nil = new RBNode(-1);

    RBTree(int key) {
        nil.setColor(RBNode.BLACK);
        root = new RBNode(key);
        root.setKey(key);
        root.setParent(nil);
        root.setLeft(nil);
        root.setRight(nil);
        root.setColor(RBNode.BLACK);
    }

    public void rbInsert(RBNode node) {
        RBNode parent = this.nil;
        RBNode currentNode = this.root;

        //Traversing tree left or right depending on node's key
        while (currentNode != this.nil) {
            parent = currentNode;

            if (node.getKey() < currentNode.getKey())
                currentNode = currentNode.getLeft();

            else
                currentNode = currentNode.getRight();
        }
        //Setting parent based on traversal
        node.setParent(parent);

        //Inserting Node
        if (parent == this.nil) //if tree is empty
            this.root = node;

        else if (node.getKey() < parent.getKey())
            parent.setLeft(node);

        else
            parent.setRight(node);


        node.setLeft(this.nil);
        node.setRight(this.nil);
        node.setColor(RBNode.RED);

        rbInsertFixup(node);
    }

    public void rbInsertFixup(RBNode node) {
        RBNode uncle;

            while (node.getParent().getColor() == RBNode.RED) {
                //if(node.getParent().getParent().getLeft() != null) {
                if (node.getParent() == node.getParent().getParent().getLeft()) { //if on the left side of the subtree
                    uncle = node.getParent().getParent().getRight();

                    if (uncle.getColor() == RBNode.RED) {
                        node.getParent().setColor(RBNode.BLACK);
                        uncle.setColor(RBNode.BLACK);
                        node.getParent().getParent().setColor(RBNode.RED);
                        node = node.getParent().getParent(); //setting pointer to violating node
                    } else if (node.getParent().getRight() != null) {
                        if (node == node.getParent().getRight()) {
                            node = node.getParent();
                            leftRotate(node);
                        }
                        node.getParent().setColor(RBNode.BLACK);
                        node.getParent().getParent().setColor(RBNode.RED);
                        rightRotate(node.getParent().getParent());
                    }


                }
                //} else if (node.getParent().getParent().getRight() != null) {
                else if (node.getParent() == node.getParent().getParent().getRight()) {
                    uncle = node.getParent().getParent().getLeft();

                    if (uncle.getColor() == RBNode.RED) {
                        node.getParent().setColor(RBNode.BLACK);
                        uncle.setColor(RBNode.BLACK);
                        node.getParent().getParent().setColor(RBNode.RED);
                        node = node.getParent().getParent();
                    } else if (node.getParent().getLeft() != null) { //Uncle node is black, triangle is formed between parent, grandparent, child
                        if (node == node.getParent().getLeft()) {
                            node = node.getParent(); //pointing to violating node
                            rightRotate(node);
                        }
                        node.getParent().setColor(RBNode.BLACK);
                        node.getParent().getParent().setColor(RBNode.RED);
                        leftRotate(node.getParent().getParent());
                    }
                }
                //}
            }
            this.root.setColor(RBNode.BLACK);
    }

    public void leftRotate(RBNode node) {
        RBNode successorNode = node.getRight();
        node.setRight(successorNode.getLeft());

        if (successorNode.getLeft() != this.nil)
            successorNode.getLeft().setParent(node);

        successorNode.setParent(node.getParent());

        if (node.getParent() == this.nil)
            this.root = successorNode;

        else if (node == node.getParent().getLeft())
            node.getParent().setLeft(successorNode);

        else
            node.getParent().setRight(successorNode);

        successorNode.setLeft(node);
        node.setParent(successorNode);

    }

    public void rightRotate(RBNode node) {
        RBNode successorNode = node.getLeft();//
        node.setLeft(successorNode.getRight()); //

        if (successorNode.getRight() != this.nil)//
            successorNode.getRight().setParent(node);//

        successorNode.setParent(node.getParent());

        if (node.getParent() == this.nil)
            this.root = successorNode;

        else if (node == node.getParent().getRight())//
            node.getParent().setRight(successorNode);//

        else
            node.getParent().setLeft(successorNode);

        successorNode.setRight(node);
        node.setParent(successorNode);

    }

    public void rbTransplant(RBNode node1, RBNode node2) {
        if (node1.getParent() == this.nil)
            this.root = node2;
        else if (node1 == node1.getParent().getLeft())
            node1.getParent().setLeft(node2);
        else
            node1.getParent().setRight(node2);

        node2.setParent(node1.getParent());
    }

    public void rbDelete(RBNode node) {
        RBNode y = node;
        RBNode x;
        int yColor = y.getColor();

        if (node.getLeft() == this.nil) {
            x = node.getRight();
            rbTransplant(node, node.getRight());
        } else if (node.getParent() == this.nil) {
            x = node.getLeft();
            rbTransplant(node, node.getLeft());
        } else {
            y = treeMinimum(node.getRight()); //find successor
            yColor = y.getColor();
            x = y.getRight();

            if (y.getParent() == node)
                x.setParent(y);

            else {
                rbTransplant(y, y.getRight());
                y.setRight(node.getRight());
                y.getRight().setParent(y);
            }

            rbTransplant(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
            y.setColor(node.getColor());
        }
        if (yColor == RBNode.BLACK)
            rbDeleteFixup(x);
    }

    public RBNode treeMinimum(RBNode node) {
        RBNode current = node;

        while (current != this.nil) {
            current = current.getLeft();
        }
        return current;
    }

    public void rbDeleteFixup(RBNode node) {
        RBNode nodeSibling;
        while (node != this.nil && node.getColor() == RBNode.BLACK) {
            if (node == node.getParent().getLeft()) {
                nodeSibling = node.getParent().getRight();

                if (nodeSibling.getColor() == RBNode.RED) {
                    nodeSibling.setColor(RBNode.BLACK);
                    node.getParent().setColor(RBNode.RED);
                    leftRotate(node.getParent());
                    nodeSibling = node.getParent().getRight();
                }
                if (nodeSibling.getLeft().getColor() == RBNode.BLACK && nodeSibling.getRight().getColor() == RBNode.BLACK) {
                    nodeSibling.setColor(RBNode.RED);
                    node = node.getParent();
                } else if (nodeSibling.getRight().getColor() == RBNode.BLACK) {
                    nodeSibling.getLeft().setColor(RBNode.BLACK);
                    nodeSibling.setColor(RBNode.RED);
                    rightRotate(nodeSibling);
                    nodeSibling = node.getParent().getRight();
                }

                nodeSibling.setColor(node.getParent().getColor());
                node.getParent().setColor(RBNode.BLACK);
                nodeSibling.getRight().setColor(RBNode.BLACK);
                leftRotate(node.getParent());
                node = this.root;
            } else {
                if (node == node.getParent().getRight()) {
                    nodeSibling = node.getParent().getLeft();

                    if (nodeSibling.getColor() == RBNode.RED) {
                        nodeSibling.setColor(RBNode.BLACK);
                        node.getParent().setColor(RBNode.RED);
                        rightRotate(node.getParent());
                        nodeSibling = node.getParent().getLeft();
                    }
                    if (nodeSibling.getRight().getColor() == RBNode.BLACK && nodeSibling.getLeft().getColor() == RBNode.BLACK) {
                        nodeSibling.setColor(RBNode.RED);
                        node = node.getParent();
                    } else if (nodeSibling.getLeft().getColor() == RBNode.BLACK) {
                        nodeSibling.getRight().setColor(RBNode.BLACK);
                        nodeSibling.setColor(RBNode.RED);
                        leftRotate(nodeSibling);
                        nodeSibling = node.getParent().getLeft();
                    }

                    nodeSibling.setColor(node.getParent().getColor());
                    node.getParent().setColor(RBNode.BLACK);
                    nodeSibling.getLeft().setColor(RBNode.BLACK);
                    rightRotate(node.getParent());
                    node = this.root;
                }
            }

        }
    }

    /**
     * Source: Dat Nguyen
     * Refactored in Java
     */
    public void drawTree(RBNode node, String indentation, boolean leafParent) {
        if(node != nil) {
            System.out.print(indentation);
            if(node == root) {
                System.out.print("root-----");
                indentation += "         ";
            }
            else if(leafParent){
                System.out.print("R-------");
                indentation += "         ";
            }
            else{
                System.out.print("L--------");
                indentation += "|         ";
            }

            System.out.println(node.getKey() + " (" + (node.getColor() == 1 ? "Black" : "Red") + ")");
            drawTree(node.getLeft(), indentation, false);

            drawTree(node.getRight(), indentation, true);
        }
    }
}
