/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puff;

/**
 * A class representing a node in a Huffman tree.
 * 
 * @author Sam Freedman
 */
public class TreeNode {
    protected TreeNode left;
    protected TreeNode right;
    protected int wt;
    
    
    public TreeNode(TreeNode l, TreeNode r, int w) {
        left = l;
        right = r;
        wt = w;
    }
    public TreeNode getLeft() {
        return left;
    }
    public TreeNode getRight() {
        return right;
    }
    public int getWt() {
        return wt;
    }
    public boolean isLeaf() {
        return false;
}
}
