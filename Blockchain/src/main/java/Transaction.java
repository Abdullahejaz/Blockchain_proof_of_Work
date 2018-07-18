import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transaction {

    // this is also the hash of the transaction.
    public String txID;
    // senders address/public key.
    public PublicKey sender;
    //Receiver Address/Public key
    public PublicKey receiver;
    public float value;
    //To help ensure none tampers the data
    public byte[] signature;
    //To keep count almost how many transactions generated
    private static int sequence = 0;

    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();


    public Transaction(PublicKey from, PublicKey to, float value,  ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.receiver = to;
        this.value = value;
        this.inputs = inputs;
    }

    // This Calculates the transaction hash (which will be used as its Id)
    private String calulateHash() {
        sequence++; //increase the sequence to avoid 2 identical transactions having the same hash
        return Utility.applySHA256_Algo(
                Utility.getStringFromKey(sender) +
                        Utility.getStringFromKey(receiver) +
                        Float.toString(value) + sequence
        );
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = Utility.getStringFromKey(sender) + Utility.getStringFromKey(receiver) + Float.toString(value)	;
        signature = Utility.applyECDSASig(privateKey,data);
    }
    //Verifies the data we signed hasnt been tampered with
    public boolean verifiySignature() {
        String data = Utility.getStringFromKey(sender) + Utility.getStringFromKey(receiver) + Float.toString(value)	;
        return Utility.verifyECDSASig(sender, data, signature);
    }

}
