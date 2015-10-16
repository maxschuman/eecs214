/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * #feelthechafe #feelit #areyoufeelingitnowSam #areyou #chafeeforprez #chafeeforking #chafeeformortysjob
 */
package huff;

import java.io.FileNotFoundException;
import java.io.IOException;




/**
 *
 * @author Admin
 */
public class main {
    public static TreeNode[] HuffmanIterate(TreeNode[] nodes){
        int a = -1, b = -1; //a is smallest weight, b is next smallest weight, both are indices of the position of these nodes in the array
        int minWeight = Integer.MAX_VALUE, nextLeast = Integer.MAX_VALUE;
        TreeNode n; //feel the chafe
        TreeNode[] result = new TreeNode[nodes.length - 1];
        int result_index = 0;
        for(int i = 0; i < nodes.length; i++){
            n = nodes[i];
            
            
            if(n.getWt() < minWeight){
                if(b >= 0){
                    result[result_index] = nodes[b];
                    result_index++;
                }
                
                b = a;
                nextLeast = minWeight;
                a = i;
                minWeight = n.getWt();
            }
            
            else if(n.getWt() < nextLeast){
                if(b >= 0){
                    result[result_index] = nodes[b];
                    result_index++;
                }
                
                b = i;
                nextLeast = n.getWt();
            }
            else{
                result[result_index] = n;
                result_index++;
            }
        }
        
        TreeNode c = new TreeNode(nodes[a], nodes[b], nodes[a].getWt()+ nodes[b].getWt());
        result[result_index] = c;
        
        return result;
    }   
        
    
    public static String findPath(TreeNode t, int ch) {
        todoList todo = new todoList(t, null, ""); //not done
        
        while (todo.getNext() != null) 
        {
            if (todo.getNode().isLeaf() == true) {
                if (((TreeLeaf)todo.getNode()).getChar() == ch) {
                    return todo.getPath();
                }
                    
            }
            else {
                
                todoList left = new todoList(todo.getNode().getLeft(), null, todo.getPath() + "0");
                todo.add(left);
                todoList right = new todoList(todo.getNode().getRight(), null, todo.getPath() + "1");
                todo.add(right);
            }
            todo = todo.getNext();
        }
        System.out.print("FAILURE");
        return "";
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException{
        FreqCounter[] freqarray = new FreqCounter[256];
        FreqCounter f;
        for(int i = 0; i < 256; i++){
            f = new FreqCounter(i, 0);
            freqarray[i] = f;
        }
        
        BitReader in = null;
        
        try{
            in = new BitReader("C:\\Users\\Admin\\Documents\\1 Fall 2015\\EECS 214\\feelthechafe.txt");
        }
        catch(Exception e){
            System.out.print("Fuck you Jesse");
            return;
        }
        
        int chafe;
        while(!(in.isEof())){
            chafe = in.readBits(8);
            
            freqarray[chafe].Increment();
        }
        
        
        int chafee = 0;
        for(FreqCounter c: freqarray) {
            if (c.GetCount() != 0) {
                chafee++;
            }
        }
        TreeNode[] nodes = new TreeLeaf[chafee];
        int i = 0;
        for(FreqCounter c: freqarray) {
            if (c.GetCount() != 0) {
                nodes[i] = new TreeLeaf(c.GetVal(), c.GetCount());
                i++;
            }
        }
        
        while(nodes.length > 1){
            nodes = HuffmanIterate(nodes);
        }
        
        System.out.println(nodes[0].getWt());
        
        //would be testing if i didn't have a failed file read
        String x = findPath(nodes[0],101);
        System.out.println(x);
        
   
    }
}
