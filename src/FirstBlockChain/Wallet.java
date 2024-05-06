package FirstBlockChain;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    public PrivateKey privateKey;
    public PublicKey publicKey;
    public Wallet(){

    }
    public void generateKeyPair(){
        try{
            KeyPairGenerator KeyGen =KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom Random =SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec =new ECGenParameterSpec("prime192v1");

            KeyGen.initialize(ecSpec,Random);
            KeyPair keyPair =KeyGen.generateKeyPair();
            privateKey =keyPair.getPrivate();
            publicKey =keyPair.getPublic();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
