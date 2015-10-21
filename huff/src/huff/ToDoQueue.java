/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huff;

/**
 * A queue holding ToDoNodes to check for encoding in the findPath method in Encode.
 * 
 * @author Max
 */
public class ToDoQueue {
    private ToDoNode head;
    private ToDoNode tail;
    
    public ToDoQueue(ToDoNode n) { 
        head = n;
        tail = n;
    }
    public ToDoNode getHead() {
        return head;
    }
    
    public ToDoNode getTail(){
        return tail;
    }
    
    public void add(ToDoNode n) {
        ToDoNode h = head;
        
        
        while (h.getNext() != null) {
            h = h.getNext();
        }
        
        h.setNext(n);
        tail = n;
    }
    
    public void removeEnd(){
        ToDoNode h = head;
        
        
        while (h.getNext() != null) {
            h = h.getNext();
        }
        
        if(h == head){
            head = null;
            tail = null;
        }
        
        tail = h;
    }
    
    public void removeFront(){
        head = head.getNext();
    }
}

