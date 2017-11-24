package com.zc.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class HaffmanTree {

    public TreeNode root;

    static class TreeNode<T> implements Comparable<TreeNode<T>>{
        public T data;
        public int weight;
        TreeNode left;
        TreeNode right;
        TreeNode parant;

        public TreeNode(T data , int weight){
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(TreeNode<T> treeNode) {
            if(this.weight > treeNode.weight){
                return -1;
            }else if(this.weight< treeNode.weight){
                return 1;
            }
            return 0;
        }
    }


    public void createHumanTree(ArrayList<TreeNode<String>> nodes){
        if(nodes == null){
            return;
        }else {
            while (nodes.size() > 1){
                Collections.sort(nodes);
                TreeNode left = nodes.get(nodes.size() -1);
                TreeNode right = nodes.get(nodes.size() - 2);
                TreeNode parant = new TreeNode("parant",left.weight+right.weight);
                parant.left = left;
                parant.right = right;
                left.parant = parant;
                right.parant = parant;
                nodes.remove(left);
                nodes.remove(right);
                nodes.add(parant);
            }
            if(nodes.size() > 0){
                root = nodes.get(0);
            }
        }
    }

    public void showHumanTree(TreeNode root){
        if(root == null){
            return;
        }
        LinkedList<TreeNode> linkedList = new LinkedList<TreeNode>();
        linkedList.offer(root);
        while (!linkedList.isEmpty()){
            TreeNode node = linkedList.pop();
            System.out.println("node:" + node.data + "  weight:"+node.weight + " code :"+getCode(node));
            if(node.left != null){
                linkedList.offer(node.left);
            }
            if(node.right != null){
                linkedList.offer(node.right);
            }
        }
    }

    public String getCode(TreeNode node){
        String str = "";
        if(node == null || (node.right != null && node.left != null)){
            return "";
        }
        TreeNode treeNode = node;
        while (treeNode.parant != null){
            if(treeNode.parant.left == treeNode){
                str = "0"+str;
            }else if(treeNode.parant.right == treeNode){
                str = "1"+ str;
            }
            treeNode = treeNode.parant;
        }
        return str;
    }



    public static void main(String[] args){
        ArrayList<TreeNode<String>> nodes = new ArrayList<TreeNode<String>>();
        TreeNode<String> node = new TreeNode<String>("A",10);
        nodes.add(node);
        nodes.add(new TreeNode("B",30));
        nodes.add(new TreeNode("C",60));
        nodes.add(new TreeNode("D",70));
        nodes.add(new TreeNode("E",15));
        nodes.add(new TreeNode("F",100));
        nodes.add(new TreeNode("G",25));
        HaffmanTree tree = new HaffmanTree();
        tree.createHumanTree(nodes);
        tree.showHumanTree(tree.root);
    }
}

