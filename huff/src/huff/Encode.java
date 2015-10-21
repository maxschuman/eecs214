/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huff;

import java.io.FileNotFoundException;
import java.io.IOException;




/**
 *
 * @author Admin
 */
public class Encode {
    public static TreeNode[] HuffmanIterate(TreeNode[] nodes){
        int a = -1, b = -1; //a is smallest weight, b is next smallest weight, both are indices of the position of these nodes in the array
        int minWeight = Integer.MAX_VALUE, nextLeast = Integer.MAX_VALUE;
        TreeNode n;
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
    
    
    public static String findPath(TreeNode t, int ch){
        ToDoNode root = new ToDoNode(t, null, "");
        ToDoQueue todo = new ToDoQueue(root);
        ToDoNode left, right;
        
        while(todo.getHead() != null){
            if(todo.getHead().getNode().isLeaf()){
                if(((TreeLeaf)(todo.getHead().getNode())).getChar() == ch){
                    return todo.getHead().getPath();
                }
            }
            
            else{
                left = new ToDoNode(todo.getHead().getNode().getLeft(), null, todo.getHead().getPath() + "0");
                todo.add(left);
                right = new ToDoNode(todo.getHead().getNode().getRight(), null, todo.getHead().getPath() + "1");
                todo.add(right);
            }
            todo.removeFront();
        }

        return "";
    }
    
    public static void encodeTree(FreqCounter[] f, int numNodes, BitWriter out) throws IOException{
        out.writeBits(numNodes, 8); //writes to file the number of leaves and weights decoder should be expecting
        
        for(FreqCounter c : f){
            if(c.GetCount() != 0){
                out.writeBits(c.GetVal(), 8); //encodes the character represented by a leaf
                out.writeBits(c.GetCount(), 32); //encodes the weight on said leaf
            }
        }
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
            in = new BitReader(args[0]);
        }
        catch(Exception e){
            System.out.print("Failure");
            return;
        }
        String y = "";
        int ch;
        while(!(in.isEof())){
            ch = in.readBits(8);
            y += (char)ch;
            freqarray[ch].Increment();
        }
        in.close();
        
        int numLeaves = 0;
        for(FreqCounter c: freqarray) {
            if (c.GetCount() != 0) {
                numLeaves++;
            }
        }
        TreeNode[] nodes = new TreeLeaf[numLeaves];
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
        
        //System.out.println(nodes[0].getWt());
        String x = "";
        
        for(char c : y.toCharArray()){
            x += findPath(nodes[0],(int)c);
        }
        short pad = 0;
        while(x.length() % 8 != 0){
            x = "0" + x;
            pad++;
        }
        
        BitWriter out = new BitWriter(args[1]);
        out.writeBits(pad, 8); //encodes the padding in the front of the message, which should be read and discarded before use
        encodeTree(freqarray, numLeaves, out); //encodes data used to generate huffman tree
        
        for(int j = 0; j < x.length(); j += 8){
            out.writeBits(Integer.parseInt(x.substring(j, j + 8), 2), 8);
        }
        
        out.close();
        
        
   
    }
}
