package FirstBlockChain;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Testing {
    public static ArrayList<Block> blockchain =new ArrayList<>();
    public static int difficulity =1;
    public static void main(String[] args) {
        /* Dynamicly save in array
        FirstBlockChain.Block genesisBlock =new FirstBlockChain.Block("This is First block","0");
        System.out.println("Hash For fist FirstBlockChain.Block is: "+genesisBlock.hash);
        FirstBlockChain.Block NextBlock =new FirstBlockChain.Block("This is the second block",genesisBlock.hash);
        System.out.println("Hash For Second FirstBlockChain.Block is: "+NextBlock.hash);
        FirstBlockChain.Block ThirdBlock =new FirstBlockChain.Block("This is the third BLock",NextBlock.hash);
        System.out.println("Hash For Third FirstBlockChain.Block is: "+ThirdBlock.hash);
         */
//        Save in Arraylist
        blockchain.add(new Block("Hi this is the first block","0"));
        System.out.println("Trying to Mine FirstBlockChain.Block 1....");
        blockchain.get(0).mineBlock(difficulity);

        blockchain.add(new Block("Hi this is the Second block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine FirstBlockChain.Block 2...");
        blockchain.get(1).mineBlock(difficulity);
        blockchain.add(new Block("Hi this is the Third block",blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine FirstBlockChain.Block 3....");
        blockchain.get(2).mineBlock(difficulity);

        System.out.println("\nblockchain is valid: "+isChainValid());
        String BlockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe BLock Chain is: ");
        System.out.println(BlockChainJson);
    }
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.CalculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }
}
