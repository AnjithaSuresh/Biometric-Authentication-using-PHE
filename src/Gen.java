import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.*;

public class Gen {
    public static HashMap<Character, BigInteger> generatekey() {
        SecureRandom random = new SecureRandom();
        HashMap<Character, BigInteger> keys = new HashMap<Character, BigInteger>();
        int n; 
        System.out.print("Enter the key size: ");
        Scanner sc= new Scanner(System.in);
        n=sc.nextInt();

        BigInteger p = safePrime(n, random);
        BigInteger q = safePrime(n, random);
        BigInteger N = p.multiply(q);
        BigInteger z;
        
        BigInteger jp,jq;
        
        do {
            z = uniformX(N, p, q, random);
            jp = jacobiSymbol(z, p);
            jq = jacobiSymbol(z, q);
        } while ((jp.equals(BigInteger.ONE)) || (jq.equals(BigInteger.ONE)));
        
        keys.put('z', z);
        keys.put('N', N);
        keys.put('p', p);
        keys.put('q', q);
        System.out.println(keys);
        return keys;
    }

    public static BigInteger safePrime(int bitLength, SecureRandom random) {
        BigInteger p, q;
        q = BigInteger.probablePrime(bitLength - 1, random);
        p = q.add(q).add(BigInteger.ONE);
        while (!p.isProbablePrime(1000)) {
            do {
                q = q.nextProbablePrime();
            } while (q.mod(BigInteger.TEN).equals(BigInteger.valueOf(7))
                    || !q.remainder(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)));
            p = q.add(q).add(BigInteger.ONE);
            while (p.bitLength() != bitLength) {
                q = BigInteger.probablePrime(bitLength - 1, random);
                p = q.add(q).add(BigInteger.ONE);
            }
        }
        return p;
    }

    public static BigInteger uniformX(BigInteger N, BigInteger p, BigInteger q, SecureRandom random) {
        BigInteger x = BigInteger.ZERO;
        do {
            x = new BigInteger(N.bitLength(), random);
        } while ((x.compareTo(BigInteger.ZERO) == -1) || (x.compareTo(N) == 1)
                || ((x.mod(p)).equals(BigInteger.ZERO)) || ((x.mod(q)).equals(BigInteger.ZERO)));
        return x;
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