/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puff;

/**
 *
 * @author Max!
 */
public class FreqCounter {
    private int val;
    private int count;
    
    public FreqCounter(int v, int c){
        val = v;
        count = c;
    }
    
    public void Increment(){
        count++;
    }
    
    public int GetCount(){
        return count;
    }
    
    public int GetVal(){
        return val;
    }
    
    public boolean Equals(int b){
        return b == val;
    }
    
}
