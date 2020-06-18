import java.math.BigInteger;
import java.util.*;

public class MainFn {
	static HashMap<Character, BigInteger> keys= new HashMap<Character, BigInteger>();
	
	public static void main(String args[]) {
		
		//keys=Gen.generatekey();
		BigInteger[][] enmsg= Enc.encrypt("DHGAXCgvxJZODFHJHWEGR7WYJbhjdVKJSF7274JBFKBZXHJVjhbkkjhfdHGSVKJCSJGDHvjhbjHSVIUWHOIWU8EQSIPASQNMAXNBCHSHGCZHGFYr565469HxgvvxjhbxjhgjhxJHXVSUYDKJSBCGHZZVCHJvjCBXJCBJcbJXhjvjchJHHFGVSPQPPMNnxbvasajsnaiopsythnsbcscvHZXy793hbkcjggxfchsbckejnkgrg9fhg2evjhsgxghyghKHJHDGUHGHDBHGVHJJXNJHSJBSHCVJZBXHhvcjhbdhgsdjbjchvxjh-bGFXISGDHhvh");
		for(int i=0; i<enmsg.length; ++i) {
			for(int j=0; j<8; ++j) {
				System.out.print(enmsg[i][j]+" ");
			}	
		}
		//System.out.println(enmsg);
		//Dec.decrypt(enmsg);
	}
	
	public static HashMap<Character, BigInteger> getkeys(){
		return keys;
	}
}
