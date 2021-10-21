package JDBC_Day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeScrollableResultSet {

	public static void main(String[] args) throws SQLException {
		
		
		// Create connection
		String connectionStr = "jdbc:oracle:thin:@23.22.179.40:1521:XE";
		String username = "hr";
		String password = "hr";

		Connection conn = DriverManager.getConnection(connectionStr, username, password);

		// Create statement
		
		Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

		// execute query
		
		ResultSet rs = stmnt.executeQuery("select employee_id, first_name,last_name,salary from employees ");
			
		// manipulate resultSet
		
		rs.absolute(60);
		
		System.out.println(rs.getString("employee_id"));
		System.out.println(rs.getString("first_name"));
		System.out.println(rs.getString("last_name"));
		System.out.println(rs.getString("salary"));
		System.out.println("-----------------------------------------");
		
       rs.previous();
		
		System.out.println(rs.getString("employee_id"));
		System.out.println(rs.getString("first_name"));
		System.out.println(rs.getString("last_name"));
		System.out.println(rs.getString("salary"));
		System.out.println("-----------------------------------------");
		
		
		rs.relative(-9);
		
		System.out.println(rs.getString("employee_id"));
		System.out.println(rs.getString("first_name"));
		System.out.println(rs.getString("last_name"));
		System.out.println(rs.getString("salary"));
		System.out.println("-----------------------------------------");
		
        rs.relative(50);
		
		System.out.println(rs.getString("employee_id"));
		System.out.println(rs.getString("first_name"));
		System.out.println(rs.getString("last_name"));
		System.out.println(rs.getString("salary"));
		System.out.println("-----------------------------------------");
		
		
		for(int i = 0 ; i < 6 ;i++) {
			
			rs.next();
			
			System.out.println(rs.getString("employee_id"));
			System.out.println(rs.getString("first_name"));
			System.out.println(rs.getString("last_name"));
			System.out.println(rs.getString("salary"));
			System.out.println("-----------------------------------------");
			
			
		}
		
		
// Close connection
		
		rs.close();
		stmnt.close();
		conn.close();

		System.out.println("THE END ");

	}//end method

}//end class
