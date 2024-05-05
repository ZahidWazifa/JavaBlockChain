import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Testing {
    public static ArrayList<Block> BlockChain =new ArrayList<>();

    public static void main(String[] args) {
        /* Dynamicly save in array
        Block genesisBlock =new Block("This is First block","0");
        System.out.println("Hash For fist Block is: "+genesisBlock.hash);
        Block NextBlock =new Block("This is the second block",genesisBlock.hash);
        System.out.println("Hash For Second Block is: "+NextBlock.hash);
        Block ThirdBlock =new Block("This is the third BLock",NextBlock.hash);
        System.out.println("Hash For Third Block is: "+ThirdBlock.hash);
         */
//        Save in Arraylist
        BlockChain.add(new Block("Hi this is the first block","0"));
        BlockChain.add(new Block("Hi this is the Second block",BlockChain.get(BlockChain.size()-1).hash));
        BlockChain.add(new Block("Hi this is the Third block",BlockChain.get(BlockChain.size()-1).hash));

        String BlockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(BlockChain);
        System.out.println(BlockChainJson);
    }
    public static  Boolean isChainvalid(){
        Block CurrentBlock;
        Block PreviousBlock;
        for (int i = 0; i < BlockChain.size(); i++) {
            CurrentBlock = BlockChain.get(i);
            PreviousBlock = BlockChain.get(i-1);
//            Comparing registered has and calculated has
            if (!CurrentBlock.hash.equals(CurrentBlock.CalculateHash())){
                System.out.println("Current Hashes is not equals");
                return false;
            }if (!PreviousBlock.hash.equals(CurrentBlock.previousHash)){
                System.out.println("Previous Hashes Not Equals");
                return false;
            }
        }
        return true;
    }
}
