package puff;



import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */
public class Decode {
    
    
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
    

  public static void main(String[] args) throws IOException, FileNotFoundException{
        BitReader in = new BitReader(args[0]); //open 
        //read in padding
        int padding = in.readBits(8);
        //read in the number of nodes to expect in Huffman Tree
        int numNodes = in.readBits(32);
       
        TreeNode[] nodes = new TreeNode[numNodes];
        
        for(int z =0; z < numNodes; z++){
            nodes[z] = new TreeLeaf(in.readBits(8), in.readBits(32));
        }
        
        while(nodes.length > 1){
            nodes = HuffmanIterate(nodes);
            
        }
        
        in.readBits(padding); //reads off the padding bits, so that the remainder is simply the encoded file
        
     
        
        String x = "";
        boolean sam;
        TreeNode t;
        t = nodes[0];
        while (!(in.isEof())) {     
            if (!t.isLeaf()) {
              sam = in.readBit();
              if(!sam) {
                  t = t.getLeft();
              }
              if(sam) {
                  t = t.getRight();
              }
            } 
            else {
                x += (char)((TreeLeaf)t).getChar();
                t = nodes[0];
            }   
        }
        x += (char)((TreeLeaf)t).getChar(); //ensures final character is added to file, because while loop stops one char short
        in.close();
        
        
        
        BitWriter out = new BitWriter(args[1]);
        
        for(int j = 0; j < x.length(); j++){
            out.writeBits(x.toCharArray()[j], 8);
        }
        
        out.close();
        
}

}