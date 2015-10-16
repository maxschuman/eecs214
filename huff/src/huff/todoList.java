/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huff;

/**
 *
 * @author samfreedman
 */
public class todoList {
    private TreeNode node; 
    private todoList next; 
    private String path;
    
    public todoList(TreeNode t, todoList n, String p) { //initialize with path ""
        node = t;
        next = n;
        path = p;
    }
    public TreeNode getNode() {
        return node;
    }
    public todoList getNext() {
        return next;
    }
    public void setNext(todoList n) {
        next = n;
    }
    public String getPath() {
        return path;
    }
    public void add(todoList n) {
        todoList h = new todoList(node, next, path);
        
        
        while (next != null) {
            h = h.getNext();
        }
        h.setNext(n);
    } 
}
