package com.zc.tree;

public class AVLBTree {

    private Node root;
    int size = 0;
    private static final int LH = 1;
    private static final int RH = -1;
    private static final int EH = 0;

    //以自己的右孩子为基点，往左边旋转
    public void leftRotate(Node node){
        if(node == null){
            return;
        }else {
            Node right = node.right;
            //step1
            node.right = node.right.left;
            if(node.right != null){
                node.right.parant = node;
            }

            //step2
            right.left = node;

            //step3
            right.parant = node.parant;
            if(node.parant == null){
                root = right;
            }else{
                if(node.parant.left == node){
                    node.parant.left = right;
                }else if(node.parant.right == node){
                    node.parant.right = right;
                }
            }

        }

    }

    //以自己的左孩子为基点,往右边旋转
    public void rightRotate(Node node){
        if(node == null ){
            return;
        }else{
            Node left = node.left;
            //step1
            node.left = left.right;
            if(left.right != null){
                left.right.parant = node;
            }

            //setp2
            left.right = node;
            left.parant = node.parant;
            if(node.parant == null){
                root = left;
            }else{
                if(node.parant.left == node){
                    node.parant.left = left;
                }else if(node.parant.right == node){
                    node.parant.right = left;
                }
            }

            //step3
            node.parant = left;

        }
    }

    private void rightBalance(Node t){

    }

    private void leftBalance(Node t){
        Node tl = t.left;
        switch ()
    }

















    class Node<T>{
        public int balance;
        public T data;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parant;

    }

}
