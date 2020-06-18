import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.BigInteger;


class Server
{
	public static String server(BigInteger[][] m)
	{
		String re="";
		try
		{
			//Server Database connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/biometric","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from serverdb");			
			
			
			int i,j,k;	
			String n_str;	
			BigInteger decry;
			String[] arrOfStr2;
			BigInteger[][] n=new BigInteger[m.length][8];
			BigInteger[][] multi=new BigInteger[m.length][8]; 
		
			while(rs.next())
			{
				n_str=rs.getString("enfp");     
				//System.out.println(n);
				//converting string n to biginteger array big_n
				arrOfStr2 = n_str.split(" "); 
				
				//string to matrix
				k=0;
				for (i=0;i<m.length;i++)
					for(j=0;j<8;j++){
			   			n[i][j]=new BigInteger(arrOfStr2[k]);
						k++;
					}

				//System.out.println("last index: "+k);

				//multiply eb' and eb
				for (i=0;i<m.length;i++)
					for(j=0;j<8;j++){
			   			multi[i][j]=m[i][j].multiply(n[i][j]);
					}
				

				//decrypt multiplied answer     
				decry=new BigInteger(Dec.decrypt(multi));

				//checks if decrypted value is zero
				if(decry.equals(BigInteger.ZERO))
				{
					
					//if zero getString("name") and print it and break
					re=rs.getString("name");
					return re;
				}
			}
			re="Name Not Found..!";
		
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return re;

	}

}
