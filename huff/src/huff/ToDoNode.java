/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huff;

/**
 * A class that represents a tree node to check in a ToDoQueue.
 * 
 * @author Max Schuman
 */
public class ToDoNode {
    private TreeNode node;
    private ToDoNode next;
    private String path;
    
    public ToDoNode(TreeNode n, ToDoNode nxt, String p){
        node = n;
        next = nxt;
        path = p;
    }
    
    public TreeNode getNode(){
        return node;
    }
    
    public String getPath(){
        return path;
    }
    
    public ToDoNode getNext(){
        return next;
    }
    
    public void setNext(ToDoNode nxt){
        next = nxt;
    }
}
