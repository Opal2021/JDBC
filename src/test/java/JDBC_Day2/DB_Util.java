package JDBC_Day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Util {

	private static Connection conn;
	private static Statement stmnt;
	private static ResultSet rs;

	public static void createConnection() {
		String env = ConfigurationReader.getProperty("currentEnv");
//		String host = ConfigurationReader.getProperty(env + "_db_hostName");
		
		String host = "jdbc:oracle:thin:@"+ConfigurationReader.getProperty(env + "_db_hostName")+":1521:XE";
		String user = ConfigurationReader.getProperty(env + "_db_user");
		String password = ConfigurationReader.getProperty(env + "_db_password");
//"jdbc:oracle:thin:@"+hostName+":1521:XE"
		try {
			conn = DriverManager.getConnection(host, user, password);
			System.out.println("Database connection is successful");
		} catch (SQLException e) {
			System.out.println("Database connection was failed");
			e.printStackTrace();
		}

	}

	public static ResultSet runQuery(String sql) {

		try {
			stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = stmnt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	public static void destroy() {
		try {
			if (rs != null) {

				rs.close();

			}

			if (stmnt != null) {
				stmnt.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}
	
	public static int getRowCount() {
		
		int rowCount = 0;
		
		try {
			rs.last();
			
			rowCount = rs.getRow();
			
			rs.beforeFirst();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return rowCount;
		
	}
	
	
	public static int getColumnCount () {
		
		int colCount = 0;
		
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			
			colCount = rsmd.getColumnCount();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colCount;
	}
	
	 // get column data as a list
	public static ArrayList<String> getColumnDataAsList(String columnName){
		
		ArrayList<String> datas = new ArrayList<>();
		
		
		try {
			rs.beforeFirst();
			
			while(rs.next()) {	
				datas.add(rs.getString(columnName));
			}
			
			rs.beforeFirst();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return datas;
		
	}
	
	
	public static String getCellValue(int rowNum, String columnName) {
		
		String cellValue = "";
		
		try {
			rs.absolute(rowNum);
			
			cellValue = rs.getString(columnName);
			
			rs.beforeFirst();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cellValue;
	}
	
	
	//get row data as a list
	
	public static List<String> getRowAsList(int rowNum){
		
		List<String> rowdatas = new ArrayList<>();
		
		try {
			rs.absolute(rowNum);
			
			for (int i = 1; i <= getColumnCount(); i++) {
				rowdatas.add(rs.getString(i));
			}
			
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return rowdatas;
		
	}
	
	
	// getRowData as Key value pair
	
	public static Map<String, String> getRowDataAsMap(int rowNum){
	
		Map<String, String> map = new HashMap<>();

		try {
			rs.absolute(rowNum);

			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 1; i <= DB_Util.getColumnCount(); i++) {
				String columnName = rsmd.getColumnName(i);

				String cellValue = rs.getString(columnName);

				map.put(columnName, cellValue);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	
	}
	
	public static List<Map<String, String>> getAllDataAsListOfMap() {
		// each row is represented as a map
		// and we want to get List of each row data as map
		// so the data type of my List is Map -->> since map has key and value data type
		// it becomes List< Map<String,String> >

		List<Map<String, String>> rowMapList = new ArrayList<>();
		// we can get one rowMap using getRowMap(i) methods
		// so we can iterate over each row and get Map object and put it into the List

		for (int i = 1; i <= getRowCount(); i++) {
			rowMapList.add(getRowDataAsMap(i));
		}
		return rowMapList;

	}
	
	public static void displayAllData() {

		// get the first row data | without knowing the column names
		int colCount = DB_Util.getColumnCount();
		// in order to get whole result cursor must be at before first location !

		try {
			// in order to start from beginning , we should be at beforefirst location
			rs.beforeFirst(); // this is for below loop to work
			while (rs.next() == true) { // row iteration

				for (int i = 1; i <= colCount; i++) { // column iteration
					System.out.print(rs.getString(i) + "\t");
				}
				System.out.println(); /// adding a blank line for next line
			}
			// now the cursor is at after last location
			// move it back to before first location so we can have no issue calling the
			// method again
			rs.beforeFirst(); // this is for next method that might need to be at before first location

		} catch (SQLException e) {
			System.out.println("ERROR WHILE GETTING ALL DATA");
			e.printStackTrace();
		}

	}
	
	
	
	
	
}
	
	
