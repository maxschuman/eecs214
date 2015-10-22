# eecs214
## Homework 1: Huffman Trees
### Sam Freedman and Max Schuman


### Encoder 
The main function is located in Encode.java, and illustrates the majority of our process.
What we did first is read through the file, and create a frequency table, which we stored as a freqCounter object. 
We then turned each character that had at least one usage into a TreeLeaf object, the collection of which was stored in an array. 
Then we used our HuffmanIterate method (Encode.java line 19), which is effectively an implementation of the algorithm used in class, wherein the method finds the two smallest trees by weight and combines them, creating a new array in the process. 
After this, we took each character in our input file and traversed the tree to find its path (the find path function effectively creates the path by recording when it goes left or right). As our implementation works, we have used a breadth-first search, as deeper branches are added to the end of our todoList (Encode.java line 75). This method returns the path, which we recorded in a string. 
When we had encoded the entire message, we wrote out the string as bits. 
One problem we had encoding the tree is that becasue our characters weren't all uniformly 8 bits, the string's length was not a multiple of 8 bits. In order to combat this, we have a line of code that adds a "0" to the front of the transmission until the length is proper (Encode.java line 150).

A second major task was transmitting the Huffman tree, in metadata, in the output file. We did this with the method encodeTree (encode.java line 87).
This works by first writing the number of leaves and weights the decoder should expect, and then it encodes the character and weight of each leaf using a loop. 
With this, we have encoded our file!

### Decoder
Our decoder (Decode.java) works basically as a reversal of our work this far. First if takes in the "padding" 0's and discards them. Then it creates an array of TreeNode objects of the size we gave it, and fills it with each character's value and weight. With this we can make a tree again, and we're back in business. 
Then we have the meat of the decoder, which reads in bits from the encoded file, and uses them to go left or right on a tree until a leaf is reached. One important caveat is that with our design, the last bit wasn't read because the end of file was reached, and so we had to take that in separately. 
The results are stored as characters in a string, which then rights out the original message!

### Testing
In order to test, we fed our encoder various quotes and speeches from Democratic Presidential candidate and former Rhode Island Governor Lincoln Chafee. Originally, we were getting an "o" after our text, which we realized was due to the extra bits we had added. (This allowed us to fix our padding mechanism). Eventually, the decoded messages started to perfectly match the original files. 

### Surprises/Miscellany
Although we had discussed it in class, I was still surprised at just how easy the decoder was in comparsion to the encoder. Additionally, I really had to work with my linked-list knowledge, as while first writing it I kept being confused which type of objects should be used. 


