package JDBC_Day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetColumnDataAsArrayList {

	public static void main(String[] args) {
		
		DB_Util.createConnection();
		
		DB_Util.runQuery("select * from employees");
		
		ArrayList <String> columnDatas = DB_Util.getColumnDataAsList("LAST_NAME");
		
		System.out.println(columnDatas.get(0));
		
		System.out.println(columnDatas.get(106));
		
		
		
		
		DB_Util.runQuery("select * from departments");
		
		//String cellValue = DB_Util.getCellValue(20, "Department_name");
		
		String cellValue = DB_Util.getCellValue(20, "Location_id");
		
		System.out.println(cellValue);
	
		System.out.println("-------------------------------");
		
		List<String> rowData = DB_Util.getRowAsList(13);
		
		
		for(String cell : rowData) {
			
			System.out.println(cell);
		}
		
		System.out.println("------------------------------");
		
		Map < String,String > data = DB_Util.getRowDataAsMap(4);
		
		System.out.println(data.get("DEPARTMENT_ID"));
		System.out.println(data.get("DEPARTMENT_NAME"));
		System.out.println(data.get("MANAGER_ID"));
		System.out.println(data.get("LOCATION_ID"));
		
	System.out.println("------------------------------");
	System.out.println("------------------------------");
	System.out.println("------------------------------");
		
	List<Map<String, String>> departmentTable = DB_Util.getAllDataAsListOfMap();
	
	
	for(int i = 0 ; i < departmentTable.size() ; i++) {
		
		System.out.println(departmentTable.get(i).get("DEPARTMENT_ID"));
		System.out.println(departmentTable.get(i).get("DEPARTMENT_NAME"));
		System.out.println(departmentTable.get(i).get("MANAGER_ID"));
		System.out.println(departmentTable.get(i).get("LOCATION_ID"));
		
		System.out.println("-------------------------------------");
	}
	
	DB_Util.displayAllData();
	
	
	
	
	
	
	
	DB_Util.destroy();	
		
	}

}
