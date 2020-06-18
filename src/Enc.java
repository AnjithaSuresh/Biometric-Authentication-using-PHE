import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class Enc {
    public static BigInteger[][] encrypt(String msg) {
    	
        SecureRandom random = new SecureRandom();
      
        
        BigInteger z = BigInteger.valueOf(573744);					
        BigInteger N = BigInteger.valueOf(577021);	
        
        BigInteger x = uniformX(N, random);
        
        char charr[]= msg.toCharArray();
        byte[] message= charArrayToByteArray(charr);
       
        
       // String ct="";
        BigInteger[][] enmsg= new BigInteger[msg.length()][8];
        
        for(int i=0; i<message.length; ++i) {
        	int k = message[i];
        	//System.out.print(k+" ");
        	for (int bit = 7, j=0; bit >= 0; --bit, ++j) {
        		int b = (k >>> bit) & 1;
        		//System.out.print(b+" ");
        		BigInteger m= BigInteger.valueOf(b);
                BigInteger c = (z.modPow(m, N).multiply(x.modPow(BigInteger.TWO, N))).mod(N);
                //ct=ct+c.toString()+" ";
                
                enmsg[i][j]= c;
        	}
        	
        }
        return enmsg;
        
    }

    public static BigInteger uniformX(BigInteger N, SecureRandom random) {
        BigInteger x = BigInteger.ZERO;
        do {
            x = new BigInteger(N.bitLength(), random);
        } while ((x.compareTo(BigInteger.ZERO) == -1) || (x.compareTo(N) == 1));
        return x;
    }
    
    public static byte[] charArrayToByteArray(char[] chars)
    {
            byte[] bytes = new byte[chars.length];
            for(int i=0;i<chars.length;i++) {
               bytes[i] = (byte) chars[i];
            }
            return bytes;
    } 
}