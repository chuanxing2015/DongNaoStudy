package com.zc.tree;

import org.omg.PortableInterceptor.INACTIVE;

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

    //右平衡，指的是t的右孩子引起的不平衡
    private void rightBalance(Node t){
        Node tr = t.right;
        switch (tr.balance){
            case LH:
                Node trl = tr.left;
                rightRotate(tr);
                leftRotate(t);
                switch (trl.balance){
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                }
                break;
            case RH:
                leftRotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
        }
    }

    //左平衡，指的是t的左孩子引起的不平衡
    private void leftBalance(Node t){
        Node tl = t.left;
        switch (tl.balance){
            case LH:
                rightRotate(t);
                t.balance = EH;
                tl.balance = EH;
                break;
            case RH:
                Node tlr = tl.right;
                leftRotate(tl);
                rightRotate(t);
                switch (tlr.balance){
                    case LH:
                        t.balance = RH;
                        tlr.balance = EH;
                        tl.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tlr.balance = EH;
                        tl.balance = LH;
                        break;
                    case EH:
                        t.balance = EH;
                        tlr.balance = EH;
                        tl.balance = EH;
                        break;
                }
                break;
        }
    }

    public void insert(int data){
        Node<Integer> node = new Node<Integer>(data);
        if(root == null){
            root = node;
        }else{
            Node<Integer> cur = root;
            Node<Integer> preNode = null;
            while (cur != null){
                preNode = cur;
                if(cur.data > data ){
                    cur = cur.left;
                }else if(cur.data < data){
                    cur = cur.right;
                }
            }
            if(preNode.data > data){
                preNode.left = node;
            }else if(preNode.data < data){
                preNode.right = node;
            }
            node.parant = preNode;
            //以上是插入完成

            //下边是检测树是否平衡，不平衡进行旋转
            while (preNode != null){
                if(preNode.data > data){
                    preNode.balance++;
                }else if(preNode.data < data){
                    preNode.balance--;
                }

                if(preNode.balance == 0){
                    break;
                }
                if(Math.abs(preNode.balance) == 2){
                    if(preNode.data > data){
                        leftBalance(preNode);
                    }else if(preNode.data < data){
                        rightBalance(preNode);
                    }
                    break;
                }

                preNode = preNode.parant;

            }
        }
        size++;
    }

    class Node<T>{
        public int balance = 0;
        public T data;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parant;

        public Node(T data){
            this.data = data;
        }

    }


    public static void main (String[] args){
        int[] arr = {1,2,3,4,5,6};

        AVLBTree tree = new AVLBTree();
        for(int a:arr){
            tree.insert(a);
        }

        System.out.println(tree.root.data);
    }

}
