import java.util.*;
import java.math.BigInteger;
import java.sql.*;

public class Client {
	

		 
		//Connection conn=null;
		public static  Connection dbConnector()
		{
			try {
				//Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/fingerprint","root",""); 
				return conn;
				
			}catch(Exception e)
			{
				System.out.println(e);
				return null;
			}
		}
	
	
static Connection conn = dbConnector();
	public static void main(String args[]) {
		Scanner sc=  new Scanner(System.in);
		System.out.println("Enter the fingerprint:");
		String newst=sc.next();
		sc.close();
		//Goldwasser gws=new Goldwasser();
		//Server svr=new Server();
		
		int equalchar=0;
		int max=0;
		int threshold=250;
		String matchedst="";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from client");  
			while(rs.next()) {
				String plainst=rs.getString(2);
				for(int i=0;i<newst.length();i++) {
					if(newst.charAt(i)==plainst.charAt(i)) {
						equalchar++;
					}	
				}
				if(equalchar>max) {
					max=equalchar;
					matchedst=plainst;
				}
				equalchar=0;
			}
			if(max>= threshold) {
				//System.out.println(max);
				BigInteger[][] checkst= Enc.encrypt(matchedst);
				String name = Server.server(checkst);
				System.out.println(name);
			}
			else {
				System.out.println("Sorry not a registered user!!");
				System.exit(0);
			}
			//System.out.println(rs.getInt(2));  
			conn.close(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		}
	}
