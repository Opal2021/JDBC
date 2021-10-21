package JDBC_Day2;

public class DisplayEmployeeTable {

	public static void main(String[] args) {
		
		DB_Util.createConnection();
		
		DB_Util.runQuery("select * from employees");
		
		DB_Util.displayAllData();
		
		DB_Util.destroy();
		
		
		
		
		
	}

}
