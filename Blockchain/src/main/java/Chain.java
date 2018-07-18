import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Chain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int i;

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the level of difficulty for mining");
        i = sc.nextInt();
        int difficulty_level = i;

        long startTime = System.currentTimeMillis();
        System.out.println("Mining Block 1 with difficulty level: " + difficulty_level );
        addBlock(new Block("First Block - 111010011", "0"));
        long endTime = System.currentTimeMillis();
        System.out.println("**************************************************");
        System.out.println("The time taken to mine Block 1 is " + (endTime - startTime) +" milliseconds");
        System.out.println("**************************************************\n");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);

        long startTime2 = System.currentTimeMillis();
        System.out.println("\nMining Block 2 with difficulty level: " + difficulty_level);
        addBlock(new Block("Second Block - 111111001",blockchain.get(blockchain.size()-1).hash));
        long endTime2 = System.currentTimeMillis();
        System.out.println("**************************************************");
        System.out.println("The time taken to mine Block 2 is " + (endTime2 - startTime2) +" milliseconds");
        System.out.println("**************************************************\n");
        long seconds1 = TimeUnit.MILLISECONDS.toSeconds(endTime2 - startTime2);

        long startTime3 = System.currentTimeMillis();
        System.out.println("Mining Block 3 with difficulty level: " + difficulty_level);
        addBlock(new Block("Third Block - 101011111",blockchain.get(blockchain.size()-1).hash));
        long endTime3 = System.currentTimeMillis();
        System.out.println("**************************************************");
        System.out.println("The time taken to mine Block 3 is " + (endTime3 - startTime3) +" milliseconds");
        System.out.println("**************************************************\n");
        long seconds2 = TimeUnit.MILLISECONDS.toSeconds(endTime3 - startTime3);


        System.out.println("\nBlock-chain is Valid: " + validity());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nBlock-chain is : ");
        System.out.println(blockChainJson);

        //Average time to mine three blocks
        double average =(seconds + seconds1 + seconds2)/3;
        System.out.println("Average time to mine three blocks with difficulty level " + difficulty_level + " is " + average);


        //Method to check the integrity of the blockchain
    }

    public static Boolean validity(){

        Block current_block;
        Block previous_block;
        int difficulty_level = i;

        String hashTarget = new String(new char[difficulty_level]).replace('\0', '0');

        for (int i =1; i <blockchain.size(); i++){
            current_block = blockchain.get(i);
            previous_block = blockchain.get(i-1);

            if (!current_block.hash.equals(current_block.hash_calculation())){
                System.out.println("Current hashes are different");
                return false;
            }

            if (!previous_block.hash.equals(current_block.previousBlockHash)){
                System.out.println("\nPrevious hashes are different");
                return false;
            }

            if(!current_block.hash.substring(0,difficulty_level).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }

        return true;
    }


    public static void addBlock(Block newBlock) {

        int difficulty_level = i;
        newBlock.mining(difficulty_level);
        blockchain.add(newBlock);
    }


}


