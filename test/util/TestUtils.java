package util;

import org.junit.Test;
import java.sql.*;

public class TestUtils {
	
	@Test
	public void testJDBCTools(){
		JDBCTools.setUrl("jdbc:mysql://localhost:3306/nailalldb");  
		JDBCTools.setUser("root");  
		JDBCTools.setPassword("");  
		 
		Connection con = JDBCTools.getConnection();  
		Connection con1 = JDBCTools.getConnection();  
		Connection con2 = JDBCTools.getConnection();  
		 
		//do something with con ...  
		 
		try {  
		con.close();  
		} catch (Exception e) {}  
		 
		try {  
		con1.close();  
		} catch (Exception e) {}  
		 
		try {  
		con2.close();  
		} catch (Exception e) {}  
		 
		con = JDBCTools.getConnection();  
		con1 = JDBCTools.getConnection();  
		try {  
		con1.close();  
		} catch (Exception e) {}  
		 
		con2 = JDBCTools.getConnection();  
		JDBCTools.printDebugMsg();  
		 
	}
	

	@Test
	public void testDBConnection(){
		DBConnection dbc = new DBConnection();
		java.sql.Connection conn = dbc.getConnection();
		System.out.println("conn = "+conn);
	}
	
	
}
