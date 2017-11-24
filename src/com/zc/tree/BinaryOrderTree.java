package com.zc.tree;

import java.util.NoSuchElementException;

public class BinaryOrderTree {

    private TreeNode root;




    public void put(Integer data){
        TreeNode treeNode = new TreeNode(data);
        if(root == null){
            root = treeNode;
        }else{
            TreeNode node = root;
            while (true){
                if(node.left != null && node.data > data){//节点有左孩子
                    node  = node.left;
                }else if(node.left == null && node.data > data){//节点没有左孩子了
                    treeNode.parant = node;
                    node.left = treeNode;
                    break;
                }else if(node.right == null && node.data < data){//节点没有有孩子了
                    treeNode.parant = node;
                    node.right = treeNode;
                    break;
                }else if(node.right != null && node.data < data){
                    node = node.right;
                }else if(node.data == data){
                    break;
                }
            }
        }
    }

    public void put1(Integer data){
        TreeNode treeNode = new TreeNode(data);
        if(root == null){
            root = treeNode;
        }else{
            TreeNode currentNode = root;
            TreeNode parant = null;
            while (currentNode != null){
                parant = currentNode;//记录上一个节点
                //System.out.println("currentData "+ currentNode.data + "  data :"+ data);
                if(currentNode.data > data){
                    currentNode = currentNode.left;
                }else if(currentNode.data < data){
                    currentNode = currentNode.right;
                }else {
                    break;
                }

            }

            treeNode.parant = parant;
            if(parant.data > data){
                parant.left = treeNode;
            }else if(parant.data < data){
                parant.right = treeNode;
            }

        }
    }


    private TreeNode searchNode(Integer data){
        if(root == null){
            return null;
        }else{
            TreeNode currentNode = root;
            TreeNode parant = null;
            while (currentNode != null){
                parant = currentNode;//记录上一个节点
                //System.out.println("currentData "+ currentNode.data + "  data :"+ data);
                if(currentNode.data > data){
                    currentNode = currentNode.left;
                }else if(currentNode.data < data){
                    currentNode = currentNode.right;
                }else {
                    return currentNode;
                }
            }
        }
        return  null;
    }

    /**
     * 删除节点需要考虑的因数
     *
     * 1、没有孩子
     * 2、只有左孩子
     * 3、只有又孩子
     * 4、有左右两个孩子
     *
     */

    //删除节点，一定要讲节点引用的对象都设置为null
    public void deleteData(Integer data){
        TreeNode node = searchNode(data);
        if(node != null ){
            //这个孩子没有子节点
            if(node.left == null && node.right == null){
                if(node.parant == null){
                    root = null;
                }else if(node.parant.left == node){
                    node.parant.left = null;
                }else if(node.parant.right == node){
                    node.parant.right = null;
                }
                node.parant = null;
            }else if(node.left != null && node.right == null){//这个只有左孩子
                if(node.parant == null){
                    root = node.left;
                    node.left.parant = null;
                }else{
                    if(node.parant.left == node){
                        node.parant.left = node.left; //父节点指向自己的左节点
                    }else{
                        node.parant.right = node.left;
                    }

                    node.left.parant = node.parant;//左孩子父节点只想自己的父节点
                    node.left = null;
                    node.parant = null;
                }
            }else if(node.left == null && node.right != null){//只有右孩子
                if(node.parant == null){
                    root = node.right;
                    node.right.parant = null;//新的根节点父节点也为null
                }else{
                    if(node.parant.left ==  node){
                        node.parant.left = node.right;
                    }else{
                        node.parant.right = node.right;
                    }
                    node.right.parant = node.parant;
                    node.right = null;
                    node.parant = null;
                }
            }else if(node.left != null && node.right != null){
                if(node.right.left == null){//获取左边
                    if(node.parant == null){
                        root = node.right;
                        node.right.parant = null;
                    }else{
                        if(node.parant.left == node){
                            node.parant.left = node.right;
                        }else{
                            node.parant.left = node.right;
                        }
                    }
                    node.right.left = node.left;
                    node.left.parant = node.right;
                    node.right = null;
                    node.left = null;
                }else{
                    //这里需要查找该节点右子树最左变得树，变将该节点的左子树作为到最左树的左孩子
                    TreeNode minNode = getMinLeftNode(node.right.left);
                    if(node.parant != null){
                        node.right.parant = node.parant;
                        node.right.left = node.left;
                        node.left.parant = node.right;

                    }else {
                        //1 最小节点作为根节点
                        root = minNode;

                        //2 接触minNode的各种依赖关系
                        if(minNode.right!= null){
                            minNode.parant.left = minNode.right;
                            minNode.right.parant = minNode.parant;
                            minNode.right = null;
                        }
                        minNode.parant = null;

                        //3minNode建立新的引用关系
                        minNode.left = node.left;
                        minNode.right = node.right;

                        //重新指定父亲节点
                        node.left.parant = minNode;
                        node.right.parant = minNode;

                    }
                    node.right = null;
                    node.left = null;

                }
            }
        }
    }


    private TreeNode getMinLeftNode(TreeNode node){
        TreeNode node1 = node;
        while (node.left != null){
            node = node1.left;
        }
        return node1;
    }



    class TreeNode{
        Integer data;
        TreeNode left;
        TreeNode right;
        TreeNode parant;

        TreeNode(Integer data){
            this.data = data;
        }
    }

    //中序遍历
    public void midOrderTraverse(TreeNode root){
        if(root == null){
            return;
        }
        midOrderTraverse(root.left);
        System.out.print(root.data + " ");
        midOrderTraverse(root.right);
    }


    public static void main(String[] args){
        int[] arr ={11,12,17,5,6,1,3,9,2,4,7,8,10,25};
        BinaryOrderTree tree = new BinaryOrderTree();
        for(int a : arr){
            tree.put1(a);
        }


        tree.midOrderTraverse(tree.root);

        TreeNode node = tree.searchNode(11);
        System.out.println();
        tree.deleteData(11);
        tree.midOrderTraverse(tree.root);
        System.out.println();
        System.out.println("nodeDate : " + (node != null ? node.data:"没有查到该数据"));


    }
}
