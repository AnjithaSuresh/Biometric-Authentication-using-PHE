import java.math.BigInteger;
import java.util.*;

public class Dec {
    public static String decrypt(BigInteger[][] enmsg) {
    	
    	//HashMap<Character, BigInteger> keys= MainFn.getkeys(); 
        BigInteger p = BigInteger.valueOf(587); 					//keys.get('p');
        BigInteger q = BigInteger.valueOf(983);						//keys.get('q');
        
        String str="";
        String demsg="";
        for(int i=0, d=0; i<enmsg.length; ++i, ++d) {
        	for(int j=0; j<8; ++j) {
        		BigInteger c= enmsg[i][j];
            	BigInteger jp = jacobiSymbol(c, p);
                BigInteger jq = jacobiSymbol(c, q);
                
                BigInteger m;
                if ((jp.equals(BigInteger.ONE)) && (jq.equals(BigInteger.ONE)))
                    m = BigInteger.ZERO;
                else
                    m = BigInteger.ONE;
                str=str+ m.toString();                
        	}
             	int ch= Integer.parseInt(str, 2); 	//char ch= (char)Integer.parseInt(str,2);
             	demsg=demsg+ch;
             	str="";
             
        }
        //System.out.print("\n"+str+"\n");
       // System.out.println("\n"+demsg);
        return demsg;
    }
    
    public static BigInteger jacobiSymbol(BigInteger a, BigInteger N) {
        BigInteger b = a.mod(N);
        BigInteger c = N;
        BigInteger s = BigInteger.ONE;
        BigInteger THREE = BigInteger.valueOf(3);
        BigInteger FOUR = BigInteger.valueOf(4);
        BigInteger FIVE = BigInteger.valueOf(5);
        BigInteger EIGHT = BigInteger.valueOf(8);

        while (b.compareTo(BigInteger.TWO) >= 0) {
            while ((b.mod(FOUR)).equals(BigInteger.ZERO)) {
                b = b.divide(FOUR);
            }
            if (b.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                if ((c.mod(EIGHT).equals(THREE)) || (c.mod(EIGHT).equals(FIVE)))
                    s = s.negate();
                b = b.divide(BigInteger.TWO);
            }
            if (b.equals(BigInteger.ONE))
                break;
            if (((b.mod(FOUR)).equals(c.mod(FOUR))) && ((b.mod(FOUR)).equals(THREE))) {
                s = s.negate();
            }
            BigInteger temp = b;
            b = c.mod(b);
            c = temp;
        }
        return s.multiply(b);
    }
}