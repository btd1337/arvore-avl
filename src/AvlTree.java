public class AvlTree {

    private Node root;

    public Node search(int value, Comparator comparator) {
        Node current = root;
        while (current != null) {
            if (current.getValue() == value) {
                break;
            }
            comparator.addNumCompvalue();
            current = current.getValue() < value ? current.getRight() : current.getLeft();
        }
        comparator.finishComparation();
        return current;
    }

    public void insert(int value, boolean balanceamento) {
        root = insert(root, value, balanceamento);
    }

    public void remove(int value) {
        this.root = remove(root, value);
    }

    public Node getRaiz() {
        return root;
    }

    public int higher() {
        return root == null ? -1 : root.getHigher();
    }

    private Node insert(Node avlNode, int value, Boolean isBalance) {
        if (avlNode == null) {
            return new Node(value);
        }

        else {
            if (avlNode.getValue() > value) {
                avlNode.setLeft(insert(avlNode.getLeft(), value, isBalance));
            } else if (avlNode.getValue() < value) {
                avlNode.setRight(insert(avlNode.getRight(), value, isBalance));
            } else {
                throw new RuntimeException("Error: Duplicate Key");
            }
        }

        if (isBalance) {
            return rebalance(avlNode);
        } else {
            updateHigher(avlNode);
            return avlNode;
        }
    }

    private Node remove(Node avlNode, int value) {
        if (avlNode == null) {
            return avlNode;
        } else if (avlNode.getValue() > value) {
            avlNode.setLeft(remove(avlNode.getLeft(), value));
        } else if (avlNode.getValue() < value) {
            avlNode.setRight(remove(avlNode.getRight(), value));
        } else {
            if (avlNode.getLeft() == null || avlNode.getRight() == null) {
                avlNode = (avlNode.getLeft() == null) ? avlNode.getRight() : avlNode.getLeft();
            } else {
                Node filhoMaisEsquerda = filhoMaisEsquerda(avlNode.getRight());
                avlNode.setValue(filhoMaisEsquerda.getValue());
                avlNode.setRight(remove(avlNode.getRight(), avlNode.getValue()));
            }
        }
        if (avlNode != null) {
            avlNode = rebalance(avlNode);
        }
        return avlNode;
    }

    private Node filhoMaisEsquerda(Node node) {
        Node current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    private Node rebalance(Node z) {
        updateHigher(z);
        int balance = balancingFactor(z);
        if (balance > 1) {
            if (higher(z.getRight().getRight()) > higher(z.getRight().getLeft())) {
                z = rotateLeft(z);
            } else {
                z.setRight(rotateRight(z.getRight()));
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (higher(z.getLeft().getLeft()) > higher(z.getLeft().getRight())) {
                z = rotateRight(z);
            } else {
                z.setLeft(rotateLeft(z.getLeft()));
                z = rotateRight(z);
            }
        }
        return z;
    }
    
    private int higher(Node n) {
        return n == null ? -1 : n.getHigher();
    }

    public int balancingFactor(Node n) {
        return (n == null) ? 0 : higher(n.getRight()) - higher(n.getLeft());
    }

    private Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node z = x.getRight();
        x.setRight(y);
        y.setLeft(z);
        updateHigher(y);
        updateHigher(x);
        return x;
    }

    private Node rotateLeft(Node y) {
        Node x = y.getRight();
        Node z = x.getLeft();
        x.setLeft(y);
        y.setRight(z);
        updateHigher(y);
        updateHigher(x);
        return x;
    }

    private void updateHigher(Node n) {
        n.setHigher(1 + Math.max(higher(n.getLeft()), higher(n.getRight())));
    }
}