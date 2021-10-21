package JDBC_Day2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC_UTIL_TEST {

	public static void main(String[] args) throws SQLException {
		

		DB_Util.createConnection();

		ResultSet rs = DB_Util.runQuery("Select * from employees");

		rs.next();

		System.out.println(rs.getString("employee_id"));

		System.out.println(rs.getString("salary"));

		rs.absolute(100);

		System.out.println(rs.getString("employee_id"));

		System.out.println(rs.getString("salary"));

		

		
		System.out.println(DB_Util.getRowCount());
	
		System.out.println(DB_Util.getColumnCount());
		
		
		for(int i = 1 ; i <= DB_Util.getRowCount() ; i++) {
			
			rs.absolute(i);
			
                for (int j = 1; j <= DB_Util.getColumnCount(); j++) {
				
				System.out.print(rs.getString(j)+" ,");
			
			
		}
		System.out.println("---------------------------------------");
		
		
	}	
		DB_Util.destroy();
		
	}//end method

}//end class
