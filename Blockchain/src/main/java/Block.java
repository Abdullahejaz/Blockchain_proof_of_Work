import java.util.Date;

public class Block {

    public String hash;
    public String previousBlockHash;
    private String data;
    private long timeStamp;
    private int nonce;


    public Block(String data, String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = hash_calculation();
    }



   public String hash_calculation(){
        String calculatedHash = Utility.applySHA256_Algo(
                previousBlockHash + Long.toString(timeStamp) + Integer.toString(nonce) + data );
         return calculatedHash;

   }

   //@param difficulty_level. It takes a number as a difficulty and solver for that much number of 0's
   public void mining(int difficulty_level){

        String target = new String(new char[difficulty_level]).replace('\0', '0');
        while(!hash.substring(0, difficulty_level).equals(target)){
            nonce ++;
            hash = hash_calculation();
        }

       System.out.println("Block Mining Done : " + hash);
   }



}

