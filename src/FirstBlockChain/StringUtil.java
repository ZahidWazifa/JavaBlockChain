package FirstBlockChain;

import java.security.*;
import java.util.Base64;

public class StringUtil {
    public static String applySha256(String input){
        try{
            MessageDigest Digest =MessageDigest.getInstance("SHA-256");
            byte[] hash =Digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString =new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex =Integer.toHexString(0xff & hash[i]);
                if (hex.length() ==1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
    public static byte[] applyECDSASig(PrivateKey privateKey, String input){
        Signature dsa;
        byte[] output = new byte[0];
        try{
            dsa =Signature.getInstance("ECSDA","BC");
            dsa.initSign(privateKey);
            byte[] strByte =input.getBytes();
            dsa.update(strByte);
            byte[] realsig =dsa.sign();
            output =realsig;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return output;
    }
    public static boolean verifyECDSASig(PublicKey publicKey,String data,byte[] signature){
        try{
            Signature ecdsaverify = Signature.getInstance("ECDSA","BC");
            ecdsaverify.initVerify(publicKey);
            ecdsaverify.update(data.getBytes());
            return ecdsaverify.verify(signature);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

    }
    public static String getStringFromKey(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}
