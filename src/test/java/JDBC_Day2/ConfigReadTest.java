package JDBC_Day2;

public class ConfigReadTest {

	public static void main(String[] args) {
		
		
		System.out.println(ConfigurationReader.getProperty("currentEnv"));
		
		System.out.println(ConfigurationReader.getProperty("sit_db_hostName"));
		
		System.out.println(ConfigurationReader.getProperty("dev_db_user"));
		
		System.out.println("-------------------------------------------");
		
		System.out.println(ConfigurationReader.getProperty("qa_db_hostName"));
		
		System.out.println(ConfigurationReader.getProperty("qa_db_user"));
		
		System.out.println(ConfigurationReader.getProperty("qa_db_password"));
		
		
		
		
		
		
		
		
		
	}

}
