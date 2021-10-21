package JDBC_Day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) throws SQLException {
		
		
		// Create connection
		String connectionStr = "jdbc:oracle:thin:@23.22.179.40:1521:XE";
		String username = "hr";
		String password = "hr";

		Connection conn = DriverManager.getConnection(connectionStr, username, password);

		// Create statement
		
		Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

		// execute query
		
		ResultSet rs = stmnt.executeQuery("select * from departments ");
			
		//ResultSet rs = stmnt.executeQuery("select * from employees ");
		
		ResultSetMetaData md = rs.getMetaData();
		
		
//		while(rs.next()) {
//			
//			System.out.println(rs.getString(2));
//			rs.next();
//			
//		}
		
		
		
//		System.out.println(md.getColumnCount());
//		
//		System.out.println(md.getColumnName(1));
//		
//		System.out.println(md.getColumnName(5));
//		
//		
//		for(int i = 1 ; i <= md.getColumnCount() ; i++) {
//			
//			System.out.println(md.getColumnName(i));
//			
//			
//		}
		
	//=================================================================	
		
		while(rs.next()) {
//			System.out.println(rs.getString(2));
//			rs.next();
//			
			
			for (int i = 1; i <= md.getColumnCount(); i++) {
				System.out.println(md.getColumnName(i)+" = "+ rs.getString(i));
			}
			
			System.out.println("-----------------------------------------");
		}
		
		
		
		//Clean UP
		
		rs.close();
		stmnt.close();
		conn.close();
		
	}//end method

}//end class
