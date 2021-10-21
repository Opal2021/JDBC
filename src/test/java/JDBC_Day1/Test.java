package JDBC_Day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String [] arg) throws SQLException {
		
		
		
		
	String connectionStr = "jdbc:oracle:thin:@23.22.179.40:1521:XE";
	String username = "hr";
	String password = "hr";

	Connection conn = DriverManager.getConnection(connectionStr, username, password);

	// Create statement
	
	Statement stmt = conn.createStatement();
	
		
	String s = "select employee_id,first_name,last_name from employees"	;
	
	ResultSet rs =  stmt.executeQuery(s);
	
	while(rs.next()) {
		
		int eid = rs.getInt("employee_id");
		
		String fname = rs.getString("first_name");
		
		String lname = rs.getString("last_name");
		
		System.out.println(fname + lname + eid);

	}
	
		
	stmt.close();
	rs.close();
	conn.close();
		
		
		
	}
	
}
