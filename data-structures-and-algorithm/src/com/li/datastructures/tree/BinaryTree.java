package com.li.datastructures.tree;

/**
 * @author LiXL
 * @date 2023/9/1
 */
public class BinaryTree {

    Node root;

    Node pre = null;

    public BinaryTree() {
    }

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void threadedNode() {
        threadedNode(root);
    }

    public void del(int value) {
        if (root == null) {
            return;
        }

        Node targetNode = searchTarget(value);
        if (targetNode == null) {
            return;
        }
        // 如果该二叉树只有一个结点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        Node parentNode = searchParent(value);
        if (targetNode.left == null && targetNode.right == null) {
            // 情况一：删除叶子结点
            if (parentNode.left != null && parentNode.left.value == value) {
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 情况三：删除有两颗子树的结点
            int min = searchRightTreeMin(targetNode.right);
            del(min);
            targetNode.value = min;
        } else {
            // 情况二：删除只有一颗子树的结点
            if (targetNode.left != null) {
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = targetNode.left;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = targetNode.left;
                    }
                } else {
                    // 当删除只有一颗子树时，需要考虑父结点为空的情况
                    root = targetNode.left;
                }

            } else {
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == value) {
                        parentNode.left = targetNode.right;
                    } else if (parentNode.right != null && parentNode.right.value == value) {
                        parentNode.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 查找待删除结点右子树的最小结点
     */
    public int searchRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        return target.value;
    }

    /**
     * 查找待删除的结点
     */
    public Node searchTarget(int value) {
        if (root == null) {
            return null;
        }
        return root.searchTarget(value);
    }

    /**
     * 查找当前结点的父结点
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 添加结点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 遍历线索化二叉树
     */
    public void orderThreadedBinaryTree() {
        // 定义一个临时结点，存储当前遍历的结点，从 root 开始
        Node node = root;
        while (node != null) {
            // 循环找到 leftType 为 1 的结点，随着遍历的变化，当 leftType 为 1 时
            // 说明该结点就是按照线索化处理后的有效结点
            while (node.leftType == 0) {
                node = node.left;
            }
            // 输出该有效结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点，则一直输出
            while (node.rightType == 1) {
                // 获取当前结点的后继结点
                node = node.right;
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.right;
        }
    }

    /**
     * 中序线索化二叉树
     */
    public void threadedNode(Node node) {
        // 如果当前 node 为空，则不线索化
        if (node == null) {
            return;
        }

        // 线索化左子树
        // 处理当前结点的前驱结点
        threadedNode(node.left);
        // 线索化当前结点
        if (node.left == null) {
            // 让当前结点的左指针之前前驱结点
            node.left = pre;
            // 修改当前结点的左指针类型
            node.leftType = 1;
        }

        // 处理当前结点的后继结点
        if (pre != null && pre.right == null) {
            // 让当前结点的右指针指向后继结点，逆向思维
            // 让前驱结点的右指针指向当前结点
            pre.right = node;
            pre.rightType = 1;
        }

        // 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        // 线索化右子树
        threadedNode(node.right);
    }

    public void delNode(int value) {
        if (root != null && root.value == value) {
            root = null;
        } else if (root != null) {
            root.delNode(value);
        }
    }

    public Node prevOrderSearch(int value) {
        if (root == null) {
            return null;
        }
        return root.prevOrderSearch(value);
    }

    public Node infixOrderSearch(int value) {
        if (root == null) {
            return null;
        }
        return root.infixOrderSearch(value);
    }

    public Node afterOrderSearch(int value) {
        if (root == null) {
            return null;
        }
        return root.afterOrderSearch(value);
    }

    public void prevOrder() {
        if (root != null) {
            root.prevOrder();
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    public void afterOrder() {
        if (root != null) {
            root.afterOrder();
        }
    }
}
