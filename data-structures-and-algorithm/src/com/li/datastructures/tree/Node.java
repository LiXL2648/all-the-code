package com.li.datastructures.tree;

/**
 * @author LiXL
 * @date 2023/9/1
 */
public class Node implements Comparable<Node> {

    int value;
    Node left;
    Node right;
    // 0：左子结点，1：前驱结点
    int leftType;
    // 0：右子结点，1：后继结点
    int rightType;

    public Node(int value) {
        this.value = value;
    }

    public void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public Node searchTarget(int value) {
        if (this.value == value) {
            return this;
        } else {
            if (this.left != null && this.value > value) {
                return this.left.searchTarget(value);
            } else if (this.right != null && this.value <= value) {
                return this.right.searchTarget(value);
            } else {
                return null;
            }
        }
    }

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value);
            } else if (this.right != null && this.value <= value) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 如果右子树高度-左子树高度大于1，进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 如果右子树的左子树大于右子树，先对右子树进行右旋转
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        // 如果左子树高度-右子树高度大于1，进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 如果左子树的右子树大于左子树，先进行左旋转
                left.rightRotate();
            }
            rightRotate();
        }
    }

    public void delNode(int value) {
        if (left != null && left.value == value) {
            left = null;
            return;
        }

        if (right != null && right.value == value) {
            right = null;
            return;
        }

        if (left != null) {
            left.delNode(value);
        }

        if (right != null) {
            right.delNode(value);
        }
    }

    public Node prevOrderSearch(int value) {
        if (this.value == value) {
            return this;
        }
        Node node = null;
        if (left != null) {
            node = left.prevOrderSearch(value);
        }
        if (node != null) {
            return node;
        }

        if (right != null) {
            node = right.prevOrderSearch(value);
        }
        return node;
    }

    public Node infixOrderSearch(int value) {
        Node node = null;
        if (left != null) {
            node = left.infixOrderSearch(value);
        }
        if (node != null) {
            return node;
        }

        if (value == this.value) {
            return this;
        }
        if (right != null) {
            node = right.infixOrderSearch(value);
        }
        return node;
    }

    public Node afterOrderSearch(int value) {
        Node node = null;
        if (left != null) {
            node = left.afterOrderSearch(value);
        }
        if (node != null) {
            return node;
        }

        if (right != null) {
            node = right.afterOrderSearch(value);
        }
        if (node != null) {
            return node;
        }

        if (value == this.value) {
            return this;
        }

        return null;
    }

    public void prevOrder() {
        // 输出父节点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (left != null) {
            left.prevOrder();
        }
        // 递归向右子树前序遍历
        if ((right != null)) {
            right.prevOrder();
        }
    }

    public void infixOrder() {
        // 递归向左子树前序遍历
        if (left != null) {
            left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树前序遍历
        if ((right != null)) {
            right.infixOrder();
        }
    }

    public void afterOrder() {
        // 递归向左子树前序遍历
        if (left != null) {
            left.afterOrder();
        }
        // 递归向右子树前序遍历
        if (right != null) {
            right.afterOrder();
        }
        // 输出父节点
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
