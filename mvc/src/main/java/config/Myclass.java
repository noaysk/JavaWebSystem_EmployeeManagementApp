package config;

public class Myclass {
	  private String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
	    private String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
	    private String password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "noay1003";

	 
	}