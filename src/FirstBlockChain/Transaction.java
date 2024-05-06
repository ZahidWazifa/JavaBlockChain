package FirstBlockChain;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Base64;

public class Transaction {
  public String TransactionId;
  public PublicKey Sender;
  public PublicKey Reciepient;
  public float Value;
  public byte[] signatures;

  public ArrayList<TransactionInput> inputs =new ArrayList<TransactionInput>();
  public ArrayList<TransactionOutput> outputs =new ArrayList<TransactionOutput>();

  private static int sequence  =0;

  public Transaction(PublicKey from, PublicKey to,float value, ArrayList<TransactionInput> inputs){
    this.Sender =from;
    this.Reciepient =to;
    this.Value =value;
    this.inputs =inputs;
  }

  private String CalcualateHash(){
    sequence++;
    return StringUtil.applySha256(
            StringUtil.getStringFromKey(Sender)+StringUtil.getStringFromKey(Reciepient)
                    +Float.toString(Value)+sequence
            );
  }
  private static String GetStringFromKey(Key key){
    return Base64.getEncoder().encodeToString(key.getEncoded());
  }

  public void generateSignature(PrivateKey privateKey){
    String data =StringUtil.getStringFromKey(Sender) + StringUtil.getStringFromKey(Reciepient)+Float.toString(Value);
    signatures =StringUtil.applyECDSASig(privateKey,data);

  }

  public boolean verifySignature(){
    String data = StringUtil.getStringFromKey(Sender)+StringUtil.getStringFromKey(Reciepient)+Float.toString(Value);
    return StringUtil.verifyECDSASig(Sender,data,signatures);
  }
}
