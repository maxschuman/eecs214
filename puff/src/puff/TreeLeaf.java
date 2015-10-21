/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package puff;

/**
 * A class that extends TreeNode and represents a leaf in a Huffman tree, complete with the character that leaf represents.
 * 
 * @author Sam
 */
public class TreeLeaf extends TreeNode {
    private int ch; //which character it is
    
    public TreeLeaf(int chaf, int w) {
        super(null, null, w);
        ch = chaf;
    }
    
    public int getChar(){
    return ch;
    }
    
    @Override
    public boolean isLeaf() {
        return true;
    }
}
