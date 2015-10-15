/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huff;

/**
 *
 * @author Admin
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
    
    public boolean isLeaf() {
        return true; //or "truuu"
    }
}
