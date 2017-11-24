package com.zc.tree;

import javax.swing.tree.TreeNode;
import java.util.Stack;

public class BinaryTree {

    public TreeNode rootTree;

    BinaryTree(String rootTree){
        this.rootTree = new TreeNode(rootTree);
    }

    //创建二叉树

    /**
     *                    A
     *           B                  C
     *     D          E       F
     *  G     H    I
     *
     * 前序遍历 ： ABDGHEICF
     * 中序遍历 ： GDHBIEAFC
     * 后序遍历 ： GHDIEBFCA
     *
     */
    public void createBinaryTree(TreeNode root){
        if(root == null){
            return;
        }

        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeH = new TreeNode("H");
        TreeNode nodeI = new TreeNode("I");

        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.leftChild = nodeF;
        nodeD.leftChild = nodeG;
        nodeD.rightChild = nodeH;
        nodeE.leftChild = nodeI;

    }

    //前序遍历
    public void preOrderTraverse(TreeNode root){
        if(root == null){
          return;
        }
        System.out.print(root.data);
        preOrderTraverse(root.leftChild);
        preOrderTraverse(root.rightChild);
    }

    //中序遍历
    public void midOrderTraverse(TreeNode root){
        if(root == null){
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.print(root.data);
        midOrderTraverse(root.rightChild);
    }

    //后序遍历
    public void postOrderTraverse(TreeNode root){
        if(root == null){
            return;
        }
        postOrderTraverse(root.leftChild);
        postOrderTraverse(root.rightChild);
        System.out.print(root.data);
    }


    //利用栈的方式 前序遍历
    public void preStackOrder(TreeNode root){
        if(root == null){
            return;
        }else {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            for(;!stack.isEmpty();){
                TreeNode treeNode = stack.pop();
                System.out.print(treeNode.data);
                if(treeNode.rightChild != null){
                    stack.push(treeNode.rightChild);
                }
                if(treeNode.leftChild != null){
                    stack.push(treeNode.leftChild);
                }
            }
        }

    }

    //利用栈的方式 中序遍历
    public void midStackOrder(TreeNode root){
        if(root == null){
            return;
        }else {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode currentNode = root;
            while (!stack.isEmpty() || currentNode != null){
                while (currentNode != null){
                    stack.push(currentNode);
                    currentNode = currentNode.leftChild;
                }
                currentNode = stack.pop();
                System.out.print(currentNode.data);
                currentNode = currentNode.rightChild;
            }
        }

    }

    //利用栈的方式 后续遍历
    public void postStackOrder(TreeNode root){
        if(root == null){
            return;
        }else {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            TreeNode currentNode = null;
            TreeNode preNode = null;
           while (!stack.isEmpty()){

               currentNode = stack.peek();//这里先不出栈，查询栈顶的元素
               //这里是preNode父节点 currentNode作为子节点进入
                if(preNode == null ||preNode.leftChild == currentNode || preNode.rightChild == currentNode){

                    if(currentNode.leftChild != null){//先循环压入左节点
                        stack.push(currentNode.leftChild);
                    }else if(currentNode.rightChild != null){
                       // stack.push(currentNode.rightChild);
                    }else{
                        currentNode = stack.pop();
                        System.out.print(currentNode.data);
                    }
                }else if(currentNode.leftChild == preNode){//这里是preNode作为子左节点 currentNode作为父节点进入
                    if(currentNode.rightChild != null){//这里是父节点的左节点已经出栈，但是右节点还没有入栈，不为空就入栈
                        stack.push(currentNode.rightChild);
                    }else{//没有右节点，父节点直接出栈并打印
                        currentNode = stack.pop();
                        System.out.print(currentNode.data);
                    }
                }else if(currentNode.rightChild == preNode){//这里是preNode作为子右节点 currentNode作为父节点进入，父节点没有左节点
                    currentNode = stack.pop();
                    System.out.print(currentNode.data);
                }

                preNode = currentNode;
           }

        }

    }



    class  TreeNode{
        String data;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(String str){
            data = str;
        }
    }


    public static void main(String[] args){
        BinaryTree tree = new BinaryTree("A");
        tree.createBinaryTree(tree.rootTree);
//        tree.preOrderTraverse(tree.rootTree);
//        System.out.println();
//        tree.midOrderTraverse(tree.rootTree);
//        System.out.println();
        tree.postOrderTraverse(tree.rootTree);

//        tree.midStackOrder(tree.rootTree);
//        System.out.println();
//        tree.midOrderTraverse(tree.rootTree);
        System.out.println();
        tree.postStackOrder(tree.rootTree);

    }

}
